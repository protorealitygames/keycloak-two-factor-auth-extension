package org.prg.twofactorauth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TwoFactorAuthSubmission {
    private final String deviceName;
    private final String encodedTotpSecret;
    private final String totpInitialCode;
    private final boolean overwrite;

    @JsonCreator
    public TwoFactorAuthSubmission(@JsonProperty(value = "device_name") String deviceName,@JsonProperty(value = "encoded_totp_secret") String encodedTotpSecret, @JsonProperty(value = "totp_initial_code") String totpInitialCode, @JsonProperty("should_overwrite") boolean shouldOverwrite) {
        this.deviceName = deviceName;
        this.encodedTotpSecret = encodedTotpSecret;
        this.totpInitialCode = totpInitialCode;
        this.overwrite = shouldOverwrite;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTotpInitialCode() {
        return totpInitialCode;
    }

    public String getEncodedTotpSecret() {
        return encodedTotpSecret;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public boolean isValid() {
        return deviceName != null &&
                totpInitialCode != null &&
                encodedTotpSecret != null &&
                !deviceName.isBlank() &&
                !totpInitialCode.isBlank() &&
                !encodedTotpSecret.isBlank();
    }
}
