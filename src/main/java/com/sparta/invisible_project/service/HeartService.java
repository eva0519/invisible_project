package com.sparta.invisible_project.service;

import com.sparta.invisible_project.entity.Board;
import com.sparta.invisible_project.entity.Heart;
import com.sparta.invisible_project.entity.Member;
import com.sparta.invisible_project.repository.BoardRepository;
import com.sparta.invisible_project.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public void heart(Long boardId, Member member) {

        Board board = boardRepository.findById(boardId).orElseThrow();

        if(heartRepository.existsByBoardAndMember(board, member)){
            heartRepository.deleteByBoardAndMember(board, member);
        } else {
            Heart heart = new Heart(member, board);
            heartRepository.save(heart);
        }
    }
}