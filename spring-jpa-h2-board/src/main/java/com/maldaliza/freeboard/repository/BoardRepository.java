package com.maldaliza.freeboard.repository;

import com.maldaliza.freeboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
