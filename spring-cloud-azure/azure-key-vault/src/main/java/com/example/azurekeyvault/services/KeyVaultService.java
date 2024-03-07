package com.example.azurekeyvault.services;

import com.azure.core.http.rest.PagedIterable;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.security.keyvault.secrets.models.SecretProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyVaultService {

    private final SecretClient client;

    public KeyVaultService(SecretClient client) {
        this.client = client;
    }

    public void createSecret(String key, String value) {
        client.setSecret(key, value);
    }

    public void deleteSecret(String key) {
        client.beginDeleteSecret(key);
    }

    public List<SecretProperties> findKeys() {
        PagedIterable<SecretProperties> properties = client.listPropertiesOfSecrets();
        return properties.stream().toList();
    }

    public KeyVaultSecret findByKey(String key) {
        return client.getSecret(key);
    }
}
