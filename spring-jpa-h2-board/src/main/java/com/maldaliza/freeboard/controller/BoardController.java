package com.maldaliza.freeboard.controller;

import com.maldaliza.freeboard.domain.Board;
import com.maldaliza.freeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록 보기
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
     * @param title
     * @param author
     * @param content
     * @return
     */
    @PostMapping("/post")
    public String createPostProcess(@RequestParam("title") String title,
                                    @RequestParam("author") String author,
                                    @RequestParam("content") String content) {

        log.info("create post process");

        Board board = new Board();
        board.setTitle(title);
        board.setAuthor(author);
        board.setContent(content);

        boardService.savePost(board);
        return "redirect:/";
    }
}
