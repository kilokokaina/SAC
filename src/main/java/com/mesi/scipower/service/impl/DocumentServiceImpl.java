package com.mesi.scipower.service.impl;

import com.mesi.scipower.model.Document;
import com.mesi.scipower.repository.DocumentRepository;
import com.mesi.scipower.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document save(Document e) {
        return documentRepository.save(e);
    }

    @Override
    public Document findById(Long id) {
        return documentRepository.findById(id).isPresent() ?
                documentRepository.findById(id).get() : null;
    }

    @Override
    public Document findByTitle(String title) {
        return documentRepository.findByTitle(title);
    }

    @Override
    public void delete(Document e) {
        documentRepository.delete(e);
    }

    @Override
    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }
}
