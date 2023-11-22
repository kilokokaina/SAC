package com.mesi.scipower.repository;

import com.mesi.scipower.model.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliationRepository extends JpaRepository<Affiliation, Long> {

    Affiliation findByName(String name);

}
