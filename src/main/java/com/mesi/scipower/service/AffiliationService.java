package com.mesi.scipower.service;

import com.mesi.scipower.model.Affiliation;

public interface AffiliationService {
    Affiliation save(Affiliation e);
    Affiliation findById(Long id);
    Affiliation findByName(String name);
    void delete(Affiliation e);
    void deleteById(Long id);
}
