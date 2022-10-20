package com.sparta.invisible_project.service;

import com.sparta.invisible_project.dto.BoardCommentResDto;
import com.sparta.invisible_project.dto.BoardReqDto;
import com.sparta.invisible_project.dto.ResponseDto;
import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Comments;
import com.sparta.invisible_project.entity.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.CommentRepository;
import com.sparta.invisible_project.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;

    // 게시판 전부 불러오기 (+댓글)
    @Transactional
    public BoardCommentResDto getBoardList() {
        return new BoardCommentResDto(boardRepository.findAllByOrderByModifiedAtDesc());
    }

    // 게시판 페이저로 불러오기
    public Page<Board> getBoardPagerList(int page,int size,String sortBy,boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return boardRepository.findAll(pageable);
    }

    // 게시판 하나만 불러오기 (+해당 게시글 댓글)
    @Transactional
    public ResponseDto<?> getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()->new RuntimeException("게시글을 찾을 수 없습니다"));
        List<Comments> commentsList = commentRepository.findAllByBoard(board);
        board.updateCommentList(commentsList);
        return ResponseDto.success(board);
    }

    // 권한
    // 게시판 생성
    public Board createBoard(BoardReqDto boardReqDto, Member member) {
        Board board = new Board(boardReqDto, member);
        return boardRepository.save(board);
    }

    // 권한
    // 게시판 수정
    @Transactional
    public Board editBoard(Long id, BoardReqDto boardReqDto, Member member) {
        Board board = boardRepository.findById(id).orElseThrow(()->new RuntimeException("수정할 게시글이 없습니다"));
        if (!board.getMember().getUsername().equals(member.getUsername())) {
            throw new RuntimeException("본인이 작성한 글만 수정 가능합니다");
        }
        board.update(boardReqDto);
        boardRepository.save(board);
        return board;
    }

    // 권한
    // 게시판 삭제
    @Transactional
    public void deleteBoard(Long id, Member member) {
        Board board = boardRepository.findById(id).orElseThrow(()->new RuntimeException("삭제할 게시글이 없습니다"));
        if (!board.getMember().getUsername().equals(member.getUsername())) {
            throw new RuntimeException("본인이 작성한 글만 삭제 가능합니다");
        }
        boardRepository.deleteById(id);
    }

}
