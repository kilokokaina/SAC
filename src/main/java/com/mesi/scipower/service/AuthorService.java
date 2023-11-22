package com.mesi.scipower.service;

import com.mesi.scipower.model.Author;

public interface AuthorService {
    Author save(Author e);
    Author findById(Long id);
    Author findByName(String name);
    void delete(Author e);
    void deleteById(Long id);
}
