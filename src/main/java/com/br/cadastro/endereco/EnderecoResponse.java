package com.br.cadastro.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Nesse arquivo eu vou dizer oque vai ser respondido no protocolo HTTP 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
