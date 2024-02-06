package com.mesi.scipower.repository;

import com.mesi.scipower.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    ItemModel findByItemName(String itemName);
    @Query(value = "SELECT * FROM item WHERE item_name LIKE '%:name%'", nativeQuery = true)
    List<ItemModel> findDuplicate(@Param("name") String itemName);

}
