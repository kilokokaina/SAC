package com.mesi.scipower.service;

import com.mesi.scipower.model.Document;

public interface DocumentService {
    Document save(Document e);
    Document findById(Long id);
    Document findByTitle(String title);
    void delete(Document e);
    void deleteById(Long id);
}
