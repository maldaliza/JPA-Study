package com.maldaliza.freeboard.controller;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록 보기
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        log.info("home");
        List<Board> boardList = boardService.findBoards();
        model.addAttribute("postList", boardList);
        return "board/list";
    }

    /**
     * 글 쓰기 폼 (GET)
     *
     * @return
     */
    @GetMapping("/post")
    public String createPostForm() {
        log.info("create post form");
        return "board/createPostForm";
    }

    /**
     * 글 쓰기 처리 (POST)
     *
     * @param board
     * @return
     */
    @PostMapping("/post")
    public String createPostProcess(@ModelAttribute("board") Board board) {
        log.info("create post process");
        boardService.savePost(board);
        return "redirect:/";
    }

    /**
     * 글 상세 보기
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findOne(id);
        model.addAttribute("board", board);
        return "board/detail";
    }
}
