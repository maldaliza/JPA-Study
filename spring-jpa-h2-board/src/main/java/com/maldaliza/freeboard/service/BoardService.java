package com.maldaliza.freeboard.service;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 글 저장
     * @param board
     */
    @Transactional
    public void savePost(Board board) {
        boardRepository.save(board);
    }

    /**
     * 글 단건 조회
     * @param id
     * @return
     */
    public Board findOne(Long id) {
        Board board = boardRepository.findOne(id);
        return board;
    }

    /**
     * 글 다건 조회
     * @return
     */
    public List<Board> findBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
}
