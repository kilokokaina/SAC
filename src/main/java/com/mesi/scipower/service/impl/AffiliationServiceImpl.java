//package com.mesi.scipower.service.impl;
//
//import com.mesi.scipower.model.Affiliation;
//import com.mesi.scipower.repository.AffiliationRepository;
//import com.mesi.scipower.service.AffiliationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AffiliationServiceImpl implements AffiliationService {
//
//    private final AffiliationRepository affiliationRepository;
//
//    @Autowired
//    public AffiliationServiceImpl(AffiliationRepository affiliationRepository) {
//        this.affiliationRepository = affiliationRepository;
//    }
//
//    @Override
//    public Affiliation save(Affiliation e) {
//        return affiliationRepository.save(e);
//    }
//
//    @Override
//    public Affiliation findById(Long id) {
//        return affiliationRepository.findById(id).isPresent() ?
//                affiliationRepository.findById(id).get() : null;
//    }
//
//    @Override
//    public Affiliation findByName(String name) {
//        return affiliationRepository.findByName(name);
//    }
//
//    @Override
//    public void delete(Affiliation e) {
//        affiliationRepository.delete(e);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        affiliationRepository.deleteById(id);
//    }
//}
