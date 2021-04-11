package com.maldaliza.productservice.repository.implementation;

import com.maldaliza.productservice.domain.Item;
import com.maldaliza.productservice.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        Item item = store.get(id);
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>(store.values());
        return items;
    }

    @Override
    public void update(Long itemId, Item updateParam) {

        // 1. 상품을 찾는다.
        Item item = findById(itemId);

        // 2. 찾은 상품에 수정된 정보를 넣는다.
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    // 테스트용 메소드
    public void clearStore() {
        store.clear();
    }
}
