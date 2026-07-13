package io.github.scheduleguide.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Topico;

public interface TopicoRepository
       extends CrudRepository<Topico, Long> {
    List<Topico> findByCategoriaId(Long categoriaId);
}
