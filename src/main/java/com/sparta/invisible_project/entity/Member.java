package com.sparta.invisible_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.invisible_project.security.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @NotBlank(message = "아이디를 입력해주세요")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @NotBlank(message = "이메일을 입력해주세요")
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public Member(String username, String password, String email, Authority authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

//    Specifies that a unique constraint is to be included in the generated DDL for a primary or secondary table.
//            Example:
//    @Entity
//    @Table(
//            name="EMPLOYEE",
//            uniqueConstraints=
//            @UniqueConstraint(columnNames={"EMP_ID", "EMP_NAME"})
//    ) 뭐지
//    public class Employee { ... }
}
