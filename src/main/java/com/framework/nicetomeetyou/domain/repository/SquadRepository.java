package com.framework.nicetomeetyou.domain.repository;

import com.framework.nicetomeetyou.domain.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SquadRepository extends JpaRepository<Squad, Long> {

    List<Squad> findByClienteId(Long clienteId);
}
