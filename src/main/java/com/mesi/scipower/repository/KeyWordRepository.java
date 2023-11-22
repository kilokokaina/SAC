package com.mesi.scipower.repository;

import com.mesi.scipower.model.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {

    KeyWord findByName(String name);

}
