package com.maldaliza.freeboard.repository;

import com.maldaliza.freeboard.domain.Board;

import java.util.List;

public interface BoardRepository {

    // 글 저장
    void save(Board board);

    // 글 단건 조회
    Board findOne(Long id);

    // 글 다건 조회
    List<Board> findAll();

    // 글 삭제
    void delete(Board board);
}
