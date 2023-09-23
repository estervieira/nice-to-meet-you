package com.framework.nicetomeetyou.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ImagemId implements Serializable {

    private String modeloFoto;
    private Long id;

    public ImagemId() {}

    public ImagemId(String modeloFoto, Long id) {
        super();
        this.modeloFoto = modeloFoto;
        this.id = id;
    }
}
