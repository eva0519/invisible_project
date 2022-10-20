package com.sparta.invisible_project.controller;

import com.sparta.invisible_project.security.MemberDetails;
import com.sparta.invisible_project.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/auth/heart/{board-id}")
    public void clickHeart(@PathVariable("board-id") Long boardId,
                           @AuthenticationPrincipal MemberDetails memberDetails) {
        heartService.heart(boardId, memberDetails.getMember());
    }
}