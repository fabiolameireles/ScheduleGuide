package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Categoria;

public interface CategoriaRepository
       extends CrudRepository<Categoria, Long> {
    
}
