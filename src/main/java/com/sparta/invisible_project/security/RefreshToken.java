package com.sparta.invisible_project.security;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @Column(name = "rt_key", nullable = false)
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String refreshToken) {
        this.value = value;
        // key값 그대로 value만 업데이트 한 값을 돌려줌
        return this;
    }
}
