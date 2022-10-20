package com.sparta.invisible_project.service;

import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.entity.Comments;
import com.sparta.invisible_project.entity.Heart;
import com.sparta.invisible_project.entity.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
import com.sparta.invisible_project.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    public final BoardRepository boardRepository;
    public final CommentRepository commentRepository;
    public final HeartRepository heartRepository;

    // 내 게시글 불러오기
    public ResponseDto<?> getMyBoardList(Member member) {
        return ResponseDto.success(boardRepository.findAllByMember(member).orElseThrow
                (() -> new RuntimeException("아이디로 작성된 게시글은 찾을 수 없습니다")));
    }
    // 내 댓글 불러오기
    public List<Comments> getMyCommentList(Member member) {
        return commentRepository.findAllByMember(member).orElseThrow
                (() -> new RuntimeException("아이디로 작성된 댓글은 찾을 수 없습니다"));
    }

    // 내가 좋아요한 게시글 불러오기
    public List<Heart> getMyHeartList(Member member) {
        return heartRepository.findAllByMember(member).orElseThrow
                (() -> new RuntimeException("좋아요한 글은 찾을 수 없습니다"));
    }
}
