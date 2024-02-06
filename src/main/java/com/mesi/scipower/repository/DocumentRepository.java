package com.mesi.scipower.repository;

import com.mesi.scipower.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findByTitle(String title);

}
