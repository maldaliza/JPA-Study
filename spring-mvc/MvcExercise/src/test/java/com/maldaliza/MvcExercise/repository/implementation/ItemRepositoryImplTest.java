package com.maldaliza.MvcExercise.repository.implementation;

import com.maldaliza.MvcExercise.AppConfig;
import com.maldaliza.MvcExercise.domain.Item;
import com.maldaliza.MvcExercise.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryImplTest {

    ItemRepository itemRepository;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        itemRepository = appConfig.itemRepository();
    }

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("spring", 10000, 20);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 15000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }
}