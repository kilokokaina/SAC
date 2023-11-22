package com.mesi.scipower.service.impl;

import com.mesi.scipower.model.ItemModel;
import com.mesi.scipower.repository.ItemRepository;
import com.mesi.scipower.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void save(ItemModel item) {
        itemRepository.save(item);
    }

    @Override
    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public ItemModel findById(Long itemId) {
        return itemRepository.findById(itemId).isPresent() ? itemRepository.findById(itemId).get() : null;
    }

    @Override
    public ItemModel findByName(String itemName) {
        return itemRepository.findByItemName(itemName);
    }

    @Override
    public void delete(ItemModel itemModel) {
        itemRepository.delete(itemModel);
    }

    @Override
    public void deleteById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
