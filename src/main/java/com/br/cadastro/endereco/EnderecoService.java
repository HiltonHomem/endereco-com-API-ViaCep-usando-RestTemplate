package com.br.cadastro.endereco;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoService {
    
    private final RestTemplate restTemplate;
    private final EnderecoConfig enderecoConfig;
    private final ExcelService excelService;

    /*
     * public EnderecoResponse executa(EnderecoRequest request): Método que constrói a URL 
     * usando o CEP do pedido e faz uma chamada HTTP GET para a API ViaCEP usando RestTemplate.
    */
    public List<EnderecoResponse> executa (EnderecoRequest request) throws IOException {
        List<EnderecoResponse> enderecos = new ArrayList<>();
        for (String cep : request.getCeps()) {
            String url = enderecoConfig.getApiUrl() + "/" + cep + "/json/";
            //injeto os valores recebidos do viacep dentro do objeto response
            EnderecoResponse response = restTemplate.getForObject(url, EnderecoResponse.class);
            enderecos.add(response);
        }
        
        // defino o caminho que o arquivo vai ter e uso o método saveToExcel da classe ExcelService
        String filePath = "enderecos.xlsx";
        excelService.saveToExcel(enderecos, filePath);
        
        return enderecos;
    }
}
