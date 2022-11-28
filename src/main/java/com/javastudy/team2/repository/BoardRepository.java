package com.javastudy.team2.repository;


import com.javastudy.team2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, PagingAndSortingRepository<Board, Integer> {


    @Query(value = "SELECT * FROM board ORDER BY no  DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Board> findBoardPaging(@Param("limit") int limit, @Param("offset") int offset);

    List<Board> findByTitle(String title);
    List<Board> findByTitleContaining(String title);

}