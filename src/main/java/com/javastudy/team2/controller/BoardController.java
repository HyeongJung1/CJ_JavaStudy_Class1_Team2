package com.javastudy.team2.controller;

import com.javastudy.team2.model.Board;
import com.javastudy.team2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /** 검색조건을 만족하는 해당 페이지 번호에 해당하는 문의 목록을 정렬 방식으로 조회한다. **/
    @GetMapping("/board")
    public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required = false) Integer p_num,
            @RequestParam(name = "sort-field", required = false) String sortField,
            @RequestParam(name = "sort-dir", required = false) String sortDir) {
        // @GetMapping("/board/{p_num}")
        // public ResponseEntity<Map> getAllBoards(@PathVariable(value = "p_num",
        // required=false) Integer p_num,
        // @RequestParam(name = "sort-field", required=false) String sortField,
        // @RequestParam(name = "sort-dir", required=false) String sortDir) {
        if (p_num == null || p_num <= 0)
            p_num = 1;
        if (sortField == null || sortField == "")
            sortField = "no";
        if (sortDir == null || sortDir == "")
            sortDir = "desc";

        return boardService.getPagingBoard(p_num, sortField, sortDir);
    }

    /** 신규 문의를 등록한다. **/
    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board) {
        // public Board createBoard(Board board) {
        return boardService.createBoard(board);
    }

    /** 해당 문의 번호의 정보를 조회한다. **/
    @GetMapping("/board/{no}")
    public ResponseEntity<Board> getBoardByNo(@PathVariable Integer no) {

        return boardService.getBoard(no);
    }

    /** 해당 문의 번호의 정보를 갱신한다. **/
    @PutMapping("/board/{no}")
    public ResponseEntity<Board> updateBoardByNo(@PathVariable Integer no, @RequestBody Board board) {

        return boardService.updateBoard(no, board);

    }

    /** 해당 문의 번호의 정보를 삭제한다 **/
    @DeleteMapping("/board/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(@PathVariable Integer no) {

        return boardService.deleteBoard(no);
    }

}
