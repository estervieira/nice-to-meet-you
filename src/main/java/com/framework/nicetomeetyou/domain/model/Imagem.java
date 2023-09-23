package com.framework.nicetomeetyou.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ImagemId.class)
public class Imagem {

    @Id
    private Long id;
    @Id
    private String modeloFoto;
    @Lob
    private byte[] imagem;
    private String tipoArquivo;

    public Imagem() {
    }

    public Imagem(String modeloFoto, Long id, byte[] imagem, String tipoArquivo) {
        super();
        this.modeloFoto = modeloFoto;
        this.id = id;
        this.imagem = imagem;
        this.tipoArquivo = tipoArquivo;
    }

}
