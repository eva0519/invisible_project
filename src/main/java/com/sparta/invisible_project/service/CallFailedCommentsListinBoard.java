package com.sparta.invisible_project.service;

public class CallFailedCommentsListinBoard extends Exception{
    @Override
    public String getMessage() {
        return "게시글 내의 댓글 리스트를 불러오는데 실패했습니다";
    }
}
