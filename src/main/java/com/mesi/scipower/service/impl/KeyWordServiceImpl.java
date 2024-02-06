//package com.mesi.scipower.service.impl;
//
//import com.mesi.scipower.model.KeyWord;
//import com.mesi.scipower.repository.KeyWordRepository;
//import com.mesi.scipower.service.KeyWordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KeyWordServiceImpl implements KeyWordService {
//
//    private final KeyWordRepository keyWordRepository;
//
//    @Autowired
//    public KeyWordServiceImpl(KeyWordRepository keyWordRepository) {
//        this.keyWordRepository = keyWordRepository;
//    }
//
//    @Override
//    public KeyWord save(KeyWord e) {
//        return keyWordRepository.save(e);
//    }
//
//    @Override
//    public KeyWord findById(Long id) {
//        return keyWordRepository.findById(id).isPresent() ?
//                keyWordRepository.findById(id).get() : null;
//    }
//
//    @Override
//    public KeyWord findByName(String name) {
//        return keyWordRepository.findByName(name);
//    }
//
//    @Override
//    public void delete(KeyWord e) {
//        keyWordRepository.delete(e);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        keyWordRepository.deleteById(id);
//    }
//}
