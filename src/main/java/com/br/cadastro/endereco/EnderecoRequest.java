package com.br.cadastro.endereco;

import lombok.Getter;
import java.util.List;

//Nesse arquivo eu digo oque vai ser recebido no GET do insomnia
@Getter
public class EnderecoRequest {
    
    private List<String> ceps;
}
