package com.javastudy.team2.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javastudy.team2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.javastudy.team2.model.Board;
import com.javastudy.team2.repository.BoardRepository;

import com.javastudy.team2.util.PagingUtil;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int findAllCount() {
        return (int) boardRepository.count();
    }


    public ResponseEntity<Map> getPagingBoard(Integer p_num, final String sortField, final String sortDirection) {
        Map<String, Object> result = null;


        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        //List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
        //List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());


        //final Pageable pageable = PageRequest.of(pu.getObjectStartNum(), pu.getObjectCountPerPage(), sort);
        final Pageable pageable = PageRequest.of(pu.getCurrentPageNum() - 1, pu.getObjectCountPerPage(), sort);

        //List<Board> list = boardRepository.findAll(pageable).getContent();
        List<Board> list = boardRepository.findBoardPaging(pu.getObjectCountPerPage(), ((pu.getCurrentPageNum() - 1) * pu.getObjectCountPerPage()));
        //List<Board> list = boardRepository.findByTitle("title100");
        //List<Board> list = boardRepository.findByTitleContaining("100");


        //List<Board> list = boardRepository.findByTitle("100", pageable).getContent();





        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        if (list == null || list.size() == 0) {
            //return null;
        }

        //result = new HashMap<>();
        result = new HashMap<String, Object>();
        result.put("pagingData", pu);
        result.put("list", list);

        return ResponseEntity.ok(result);
    }

    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }


    public Board createBoard(Board board) {
        board.setCreated(new Date());
        return boardRepository.save(board);
    }


    public ResponseEntity<Board> getBoard(Integer no) {
        Board board = boardRepository.findById(no).orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(board);
    }

    public ResponseEntity<Board> updateBoard(
            Integer no, Board updatedBoard) {
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        //board.setType(updatedBoard.getType());
        board.setTitle(updatedBoard.getTitle());
        board.setContents(updatedBoard.getContents());
        board.setUpdated(new Date());

        Board endUpdatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(endUpdatedBoard);
    }


    public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer no) {
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));

        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
