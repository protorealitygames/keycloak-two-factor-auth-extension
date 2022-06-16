package org.keycloak.examples.twofactorauth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TwoFactorAuthVerificationData {
    private final String deviceName;
    private final String totpCode;

    @JsonCreator
    public TwoFactorAuthVerificationData(@JsonProperty(value = "device_name") String deviceName, @JsonProperty(value = "totp_initial_code") String totpInitialCode) {
        this.deviceName = deviceName;
        this.totpCode = totpInitialCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTotpCode() {
        return totpCode;
    }

    public boolean isValid() {
        return deviceName != null &&
                totpCode != null &&
                !deviceName.isBlank() &&
                !totpCode.isBlank();
    }
}
