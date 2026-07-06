package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Conteudo;

public interface ConteudoRepository
       extends CrudRepository<Conteudo, Long> {
    
}
