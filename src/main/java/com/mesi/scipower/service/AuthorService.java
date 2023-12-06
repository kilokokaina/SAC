package com.mesi.scipower.service;

import com.mesi.scipower.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author e);
    Author findById(Long id);
    Author findByName(String name);
    List<Author> findAll();
    void delete(Author e);
    void deleteById(Long id);
}
