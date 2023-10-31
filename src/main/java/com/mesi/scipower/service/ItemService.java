package com.mesi.scipower.service;

import com.mesi.scipower.model.ItemModel;

import java.util.List;

public interface ItemService {
    void save(ItemModel item);
    List<ItemModel> findAll();
    ItemModel findById(Long itemId);
    ItemModel findByName(String itemName);
    void delete(ItemModel itemModel);
    void deleteById(Long itemId);
}
