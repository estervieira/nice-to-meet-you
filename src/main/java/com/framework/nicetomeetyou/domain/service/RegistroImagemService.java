package com.framework.nicetomeetyou.domain.service;

import com.framework.nicetomeetyou.domain.model.Imagem;
import com.framework.nicetomeetyou.domain.repository.ImagemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@Service
public class RegistroImagemService {

    private final ImagemRepository imagemRepository;

    @Transactional
    public ResponseEntity<String> saveImagem(String modeloFoto, Long id, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] imagemBytes = inputStream.readAllBytes();

            Imagem imagem = new Imagem();
            imagem.setModeloFoto(modeloFoto);
            imagem.setId(id);
            imagem.setImagem(imagemBytes);
            imagem.setTipoArquivo(file.getContentType());

            imagemRepository.save(imagem);

            return ResponseEntity.ok("Imagem salva com sucesso");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem");
        }
    }

    @Transactional
    public ResponseEntity<String> updateImagem(String tabela, Long id, MultipartFile file) {
        try {

            Imagem imagem = imagemRepository.findByModeloFotoAndId(tabela, id);
            if (imagem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada");
            }

            InputStream inputStream = file.getInputStream();
            byte[] imagemBytes = inputStream.readAllBytes();

            imagem.setImagem(imagemBytes);
            imagem.setModeloFoto(file.getContentType());

            imagemRepository.save(imagem);

            return ResponseEntity.ok("Imagem atualizada com sucesso");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a imagem");
        }
    }

    @Transactional
    public Imagem getImagem(String modeloFoto, Long id) {
        return imagemRepository.findByModeloFotoAndId(modeloFoto, id);
    }

    @Transactional
    public boolean deleteImagem(String modeloFoto, Long id) {
        Imagem imagem = imagemRepository.findByModeloFotoAndId(modeloFoto, id);
        if (imagem != null) {
            imagemRepository.delete(imagem);
            return true;
        }
        return false;
    }

}
