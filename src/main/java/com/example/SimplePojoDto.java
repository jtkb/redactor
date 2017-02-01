package com.example;

import com.example.security.SecurityLevel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SimplePojoDto {

    @NotNull
    @Valid
    private String publicName;

    @NotNull
    @Valid
    // Can only be seen at TOP_SECRET level
    @Redactable(allowSecurityLevel = SecurityLevel.TOP_SECRET)
    private String secretName;

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getSecretName() {
        return secretName;
    }

    public void setSecretName(String secretName) {
        this.secretName = secretName;
    }
}
