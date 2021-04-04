package com.maldaliza.MvcExercise.repository.implementation;

import com.maldaliza.MvcExercise.domain.Item;
import com.maldaliza.MvcExercise.repository.ItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepositoryImpl implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long itemId) {
        Item item = store.get(itemId);
        return item;
    }

    @Override
    public List<Item> findAll() {
        ArrayList<Item> items = new ArrayList<>(store.values());
        return items;
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
