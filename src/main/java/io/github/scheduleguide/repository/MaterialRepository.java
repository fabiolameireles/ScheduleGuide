package io.github.scheduleguide.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Material;

public interface MaterialRepository
       extends CrudRepository<Material, Long> {
 
       List<Material> findByConteudoId(Long TopicoId);

}
