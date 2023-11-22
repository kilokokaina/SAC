package com.mesi.scipower.service.impl;

import com.mesi.scipower.model.Author;
import com.mesi.scipower.repository.AuthorRepository;
import com.mesi.scipower.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author e) {
        return authorRepository.save(e);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).isPresent() ?
                authorRepository.findById(id).get() : null;
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public void delete(Author e) {
        authorRepository.delete(e);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
