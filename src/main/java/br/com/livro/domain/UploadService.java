package br.com.livro.domain;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class UploadService {

    public String upload(String fileName, InputStream in) throws IOException {
        if (fileName == null || in == null) {
            throw new IllegalArgumentException("Parâmetros inválidos");
        }

        // Pasta temporaria da JVM
        File tmpDir = new File(System.getProperty("java.io.tmpdir"), "carros");

        // Cria pasta carros se nao existe
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        // Cria o arquivo
        File file = new File(tmpDir, fileName);
        // Abre a OutputStream para escrever o arquivo
        FileOutputStream out = new FileOutputStream(file);

        // Escreve os dados no arquivo
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(out);

        // Retorna o caminho do arquivo
        String path = file.getAbsolutePath();

        return path;
    }

}
