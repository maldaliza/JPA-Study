package com.maldaliza.freeboard.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)      // JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다.
public class Board {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    //== Board 수정 생성자 ==//
    public Board(Long id, String title, String author, String content, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    public Board() {
    }
}
