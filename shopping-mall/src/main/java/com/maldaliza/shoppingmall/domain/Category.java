package com.maldaliza.shoppingmall.domain;

import com.maldaliza.shoppingmall.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",      // 다대다 관계를 중간 테이블 CATEGORY_ITEM으로 일대다, 다대일 관계로 풀어내야한다.
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")       // 같은 클래스 내의 parent 필드에 맵핑.
    private List<Category> child = new ArrayList<>();
}
