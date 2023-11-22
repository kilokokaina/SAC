package com.mesi.scipower.service;

import com.mesi.scipower.model.KeyWord;

public interface KeyWordService {
    KeyWord save(KeyWord e);
    KeyWord findById(Long id);
    KeyWord findByName(String name);
    void delete(KeyWord e);
    void deleteById(Long id);
}
