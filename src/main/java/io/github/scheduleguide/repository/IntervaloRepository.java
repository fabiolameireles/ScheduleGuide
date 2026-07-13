package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Intervalo;

public interface IntervaloRepository
       extends CrudRepository<Intervalo, Long> {
    
}
