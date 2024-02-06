package com.mesi.scipower.repository;

import com.mesi.scipower.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    Reference findByName(String name);

}
