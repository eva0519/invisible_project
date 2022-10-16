package com.sparta.invisible_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Members extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "안대돌아가")
    @Column(nullable = false, unique = true)
    private String members_name;

    @NotBlank(message = "안대ㅅㅂ돌아가")
    @Column(nullable = false)
    private String members_password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public Members(String members_name, String members_password, Authority authority) {
        this.members_name = members_name;
        this.members_password = members_password;
        this.authority = authority;
    }
}
