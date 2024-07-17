package com.exam.alsea.repository;

import com.exam.alsea.entity.Pais;
import com.exam.alsea.entity.enums.CodigoPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    Optional<Pais> findByCodigo(CodigoPais codigo);

}
