package io.github.scheduleguide.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.scheduleguide.domain.Material;

public interface MaterialRepository
       extends CrudRepository<Material, Long> {
    
}
