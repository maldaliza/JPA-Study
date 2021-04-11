package com.maldaliza.productservice.repository;

import com.maldaliza.productservice.domain.Item;

import java.util.List;

public interface ItemRepository {

    // 상품 저장
    Item save(Item item);

    // 상품id로 상품 조회
    Item findById(Long id);

    // 모든 상품 조회
    List<Item> findAll();

    // 상품 수정
    void update(Long itemId, Item updateParam);

    // 상품 삭제
    void deleteById(Long id);
}
