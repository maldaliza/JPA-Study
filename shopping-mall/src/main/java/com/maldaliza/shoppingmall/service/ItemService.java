package com.maldaliza.shoppingmall.service;

import com.maldaliza.shoppingmall.domain.item.Item;
import com.maldaliza.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 저장
     * @param item
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 상품 조회
     * @return
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 상품 단건 조회
     * @param itemId
     * @return
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
