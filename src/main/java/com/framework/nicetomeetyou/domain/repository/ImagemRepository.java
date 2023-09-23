package com.framework.nicetomeetyou.domain.repository;

import com.framework.nicetomeetyou.domain.model.Imagem;
import com.framework.nicetomeetyou.domain.model.ImagemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, ImagemId> {
    Imagem findByModeloFotoAndId(String modeloFoto, Long id);
}
