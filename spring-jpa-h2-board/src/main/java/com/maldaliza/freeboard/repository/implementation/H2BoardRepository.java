package com.maldaliza.freeboard.repository.implementation;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class H2BoardRepository implements BoardRepository {

    private final EntityManager entityManager;

    /**
     * 글 저장
     * @param board
     */
    @Override
    public void save(Board board) {
        if (board.getId() == null) {        // id가 미리 존재하지 않는 경우
            entityManager.persist(board);
        }
    }

    /**
     * 글 단건 조회
     * @param id
     * @return
     */
    @Override
    public Board findOne(Long id) {
        Board findBoard = entityManager.find(Board.class, id);
        return findBoard;
    }

    /**
     * 글 다건 조회
     * @return
     */
    @Override
    public List<Board> findAll() {
        List<Board> boardList = entityManager.createQuery("select b from Board b", Board.class).getResultList();
        return boardList;
    }

    /**
     * 글 삭제
     * @param board
     */
    @Override
    public void delete(Board board) {
        entityManager.remove(board);
    }
}
