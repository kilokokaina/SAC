package com.mesi.scipower.service.impl;

import com.mesi.scipower.model.Reference;
import com.mesi.scipower.repository.ReferenceRepository;
import com.mesi.scipower.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;

    @Autowired
    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @Override
    public Reference save(Reference e) {
        return referenceRepository.save(e);
    }

    @Override
    public Reference findById(Long id) {
        return referenceRepository.findById(id).isPresent() ?
                referenceRepository.findById(id).get() : null;
    }

    @Override
    public Reference findByName(String name) {
        return referenceRepository.findByName(name);
    }

    @Override
    public void delete(Reference e) {
        referenceRepository.delete(e);
    }

    @Override
    public void deleteById(Long id) {
        referenceRepository.deleteById(id);
    }
}
