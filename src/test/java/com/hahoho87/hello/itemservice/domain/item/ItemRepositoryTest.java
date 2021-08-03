package com.hahoho87.hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("아이템 생성 테스트")
    void saveItemTest() {
        // given
        Item itemA = new Item("ItemA", 10000, 10);
        Item savedItem = itemRepository.save(itemA);
        // when
        Item findItem = itemRepository.findById(savedItem.getId());
        // then
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("모든 아이템 조회 테스트")
    void findAllItemsTest() {
        // given
        Item itemA = new Item("ItemA", 10000, 10);
        Item itemB = new Item("ItemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        // when
        List<Item> items = itemRepository.findAll();
        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    @DisplayName("아이템 업데이트 테스트")
    void updateItemTest() {
        // given
        Item item = new Item("ItemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();
        // when
        Item updateParam = new Item("ItemB", 20000, 20);
        itemRepository.update(id, updateParam);
        Item findItem = itemRepository.findById(id);
        // then
        assertThat(item).isEqualTo(findItem);
    }

}