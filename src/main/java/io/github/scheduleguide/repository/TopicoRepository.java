package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Topico;

public interface TopicoRepository
       extends CrudRepository<Topico, Long> {
    
}
