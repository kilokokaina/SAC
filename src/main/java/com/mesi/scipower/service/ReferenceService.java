package com.mesi.scipower.service;

import com.mesi.scipower.model.Reference;

public interface ReferenceService {
    Reference save(Reference e);
    Reference findById(Long id);
    Reference findByName(String name);
    void delete(Reference e);
    void deleteById(Long id);
}
