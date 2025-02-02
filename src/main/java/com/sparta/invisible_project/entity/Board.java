package com.sparta.invisible_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.invisible_project.dto.BoardReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
// 대다 관계성 형성에 의한 무한 참조를 막아주는 어노테이션
public class Board extends Timestamped {

    @Formula("(select count(1) from comments bc where bc.board_id = id)")
    private int totalCommentCount;

    @Formula("(select count(1) from heart bc where bc.board_id = id)")
    private int totalHeartCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> commentList = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Heart> HeartList = new java.util.ArrayList<>();

    public Board(BoardReqDto boardReqDto, Member member) {
        this.title = boardReqDto.getTitle();
        this.content = boardReqDto.getContent();
        this.author = member.getUsername();
        this.member = member;
    }

    public void update(BoardReqDto boardReqDto) {
        this.title = boardReqDto.getTitle();
        this.content = boardReqDto.getContent();
    }

    public void updateCommentList(List<Comments> commentsList) {
        this.commentList = commentsList;
    }
}
