package com.br.cadastro.endereco;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConfig {

    @Value("${endereco.api.url}")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }
}