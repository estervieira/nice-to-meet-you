package com.framework.nicetomeetyou.domain.repository;

import java.util.List;

import com.framework.nicetomeetyou.domain.model.Colaborador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    @Query( value =
            "SELECT c.* "+
                    "FROM colaborador c "+
                    "JOIN colaborador_squad cs ON c.id = cs.colaborador_id "+
                    "JOIN squad s ON cs.squad_id = s.id "+
                    "WHERE s.cliente_id = :cliente_id ",
            nativeQuery = true
    )
    List<Colaborador> findColaboradoresBySquadsClienteId(@Param("cliente_id") Long clienteId);

    @Query(value =
            "SELECT CAST(s.cliente_id AS varchar) AS cliente, COUNT(c.id) AS quantidade_colaboradores " +
                    "FROM squad s " +
                    "JOIN colaborador_squad cs ON s.id = cs.squad_id " +
                    "JOIN colaborador c ON cs.colaborador_id = c.id " +
                    "GROUP BY s.cliente_id",
            nativeQuery = true)
    List<Object[]> countColaboradoresPorCliente();

}
