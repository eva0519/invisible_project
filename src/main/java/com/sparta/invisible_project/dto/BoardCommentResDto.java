package com.sparta.invisible_project.dto;

import com.sparta.invisible_project.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentResDto {
    List<Board> boardList;
}
