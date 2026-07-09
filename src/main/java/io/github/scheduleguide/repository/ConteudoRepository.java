package io.github.scheduleguide.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Conteudo;

public interface ConteudoRepository
       extends CrudRepository<Conteudo, Long> {
       
       List<Conteudo> findByTopicoId(Long TopicoId);

}
