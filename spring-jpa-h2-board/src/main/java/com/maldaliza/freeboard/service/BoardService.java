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

    @Transactional
    public Long savePost(BoardDto boardDto) {
        // boardDto.toEntity()를 통해 boardDto를 Board 타입의 엔티티로 만든다.
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    /**
     * 글 단건 조회
     * @param id
     * @return
     */
    public BoardDto findOne(Long id) {

        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();

        return boardDto;
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
        boardRepository.deleteById(id);
    }
}
