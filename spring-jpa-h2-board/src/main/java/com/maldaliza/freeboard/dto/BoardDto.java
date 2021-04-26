package com.maldaliza.freeboard.dto;

import com.maldaliza.freeboard.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor      // 기본 생성자 생성
public class BoardDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // toEntity(): DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 일을 수행.
    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .title(title)
                .author(author)
                .content(content)
                .build();

        return build;
    }

    @Builder
    public BoardDto(Long id, String title, String author, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
