package com.br.cadastro.endereco;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;
    
    //ResponseEntity é pra retornar os status https
    @GetMapping("/consulta")
    public ResponseEntity consultaCep(@RequestBody EnderecoRequest enderecoRequest){
        try {
            return ResponseEntity.ok(enderecoService.executa(enderecoRequest));
        } catch (IOException erro) {
            erro.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o arquivo Excel.");
        }
    }
}
