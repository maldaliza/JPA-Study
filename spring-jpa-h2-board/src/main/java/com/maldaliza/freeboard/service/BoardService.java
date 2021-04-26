package com.maldaliza.freeboard.service;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.dto.BoardDto;
import com.maldaliza.freeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 글 저장
     * @param boardDto
     */
    @Transactional
    public void savePost(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
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
    public List<BoardDto> findBoards() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .author(board.getAuthor())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    /**
     * 글 삭제
     * @param id
     */
    @Transactional
    public void deleteBoard(Long id) {
        Board findBoard = boardRepository.findOne(id);
        boardRepository.delete(findBoard);
    }
}
