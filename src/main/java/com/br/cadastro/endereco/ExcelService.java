package com.br.cadastro.endereco;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void saveToExcel(List<EnderecoResponse> enderecos, String filePath) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File(filePath);

        if (file.exists()) {
            // Se o arquivo já existe, abra-o
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.getSheetAt(0);
            }
        } else {
            // Se o arquivo não existe, crie um novo
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Endereços");
            // Crie a linha de cabeçalho
            Row headerRow = sheet.createRow(0);
            String[] columns = {"CEP", "Logradouro", "Bairro", "Localidade", "UF"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }
        }

        // Determine a próxima linha disponível
        int rowNum = sheet.getLastRowNum() + 1;

        // Preencher linhas de dados
        for (EnderecoResponse endereco : enderecos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(endereco.getCep());
            row.createCell(1).setCellValue(endereco.getLogradouro());
            row.createCell(2).setCellValue(endereco.getBairro());
            row.createCell(3).setCellValue(endereco.getLocalidade());
            row.createCell(4).setCellValue(endereco.getUf());
        }

        // Redimensionar todas as colunas para se ajustarem ao tamanho do conteúdo
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrever a saída para um arquivo
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}