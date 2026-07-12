package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Dia;

public interface DiaRepository
       extends CrudRepository<Dia, Long> {
    
}
