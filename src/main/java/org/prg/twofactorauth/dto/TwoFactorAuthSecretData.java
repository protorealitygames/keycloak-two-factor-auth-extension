package org.prg.twofactorauth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TwoFactorAuthSecretData {
    private final String encodedTotpSecret;
    private final String totpSecretQRCode;

    @JsonCreator
    public TwoFactorAuthSecretData(@JsonProperty(value = "encoded_totp_secret", required = true) String encodedTotpSecret, @JsonProperty(value = "totp_secret_qr_code", required = true) String totpSecretQRCode) {
        this.encodedTotpSecret = encodedTotpSecret;
        this.totpSecretQRCode = totpSecretQRCode;
    }
    
    public String getEncodedTotpSecret() {
		return encodedTotpSecret;
	}
    
    public String getTotpSecretQRCode() {
		return totpSecretQRCode;
	}
}
