package com.mesi.scipower.repository;

import com.mesi.scipower.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    ItemModel findByItemName(String itemName);

}
