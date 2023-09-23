package com.framework.nicetomeetyou.api.controller;


import com.framework.nicetomeetyou.domain.model.Imagem;
import com.framework.nicetomeetyou.domain.service.RegistroImagemService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final RegistroImagemService registroImagemService;

    @PostMapping("/{modeloFoto}/{id}")
    public ResponseEntity<String> saveImagem(@PathVariable String modeloFoto, @PathVariable Long id,
                                             @RequestParam("file") MultipartFile file) {
        return registroImagemService.saveImagem(modeloFoto, id, file);
    }

    @PutMapping("/{modeloFoto}/{id}")
    public ResponseEntity<String> updateImagem(@PathVariable String modeloFoto, @PathVariable Long id,
                                               @RequestParam("file") MultipartFile file) {
        return registroImagemService.updateImagem(modeloFoto, id, file);
    }

    @GetMapping("/{modeloFoto}/{id}")
    public ResponseEntity<byte[]> getImagem(@PathVariable String modeloFoto, @PathVariable Long id) {
        Imagem imagem = registroImagemService.getImagem(modeloFoto, id);
        if (imagem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(imagem.getTipoArquivo())).body(imagem.getImagem());
    }

    @DeleteMapping("/{modeloFoto}/{id}")
    public ResponseEntity<String> deleteImagem(@PathVariable String modeloFoto, @PathVariable Long id) {
        boolean deleted = registroImagemService.deleteImagem(modeloFoto, id);
        if (deleted) {
            return ResponseEntity.ok("Imagem excluída com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
