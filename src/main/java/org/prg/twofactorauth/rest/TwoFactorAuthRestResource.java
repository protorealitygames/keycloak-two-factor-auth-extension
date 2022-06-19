package org.prg.twofactorauth.rest;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.services.managers.AppAuthManager;
import org.keycloak.services.managers.AuthenticationManager;

import javax.ws.rs.*;

public class TwoFactorAuthRestResource {

	private final KeycloakSession session;
    private final AuthenticationManager.AuthResult auth;
	
	public TwoFactorAuthRestResource(KeycloakSession session) {
		this.session = session;
        this.auth = new AppAuthManager.BearerTokenAuthenticator(session).authenticate();
	}

    // Same like "companies" endpoint, but REST endpoint is authenticated with Bearer token and user must be in realm role "admin"
    // Just for illustration purposes
    @Path("manage-2fa/{user_id}")
    public User2FAResource getCompanyResource(@PathParam("user_id") final String userid) {
        final UserModel user = checkPermissionsAndGetUser(userid);
        return new User2FAResource(session, user);
    }

    private UserModel checkPermissionsAndGetUser(final String userid) {
        if (auth == null) {
            var auth = new AppAuthManager.BearerTokenAuthenticator(session);
            auth.authenticate();
            throw new NotAuthorizedException("Bearer");
        } else if (auth.getUser().getServiceAccountClientLink() == null) {
            throw new ForbiddenException("Not service account");
        } else if (auth.getToken().getRealmAccess() == null || !auth.getToken().getRealmAccess().isUserInRole("manage-2fa")) {
            throw new ForbiddenException("Does not have realm manage-2fa role");
        }

        final UserModel user = this.session.users().getUserById(this.session.getContext().getRealm(), userid);
        if (user == null) {
            throw new BadRequestException("invalid user");
        }
        if (user.getServiceAccountClientLink() != null) {
            throw new ForbiddenException("Cannot manage 2fa of service account");
        }

        return user;
    }

}
