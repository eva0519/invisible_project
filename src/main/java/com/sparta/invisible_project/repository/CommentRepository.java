package com.sparta.invisible_project.repository;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Comments;
import com.sparta.invisible_project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByBoard(Board board);
    Optional<List<Comments>> findAllByMember(Member member);
}
