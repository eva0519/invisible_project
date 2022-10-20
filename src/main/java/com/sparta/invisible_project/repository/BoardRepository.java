package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    Optional<List<Board>> findAllByMember(Member member);

    List<Board> findAllByOrderByIdDesc();
}