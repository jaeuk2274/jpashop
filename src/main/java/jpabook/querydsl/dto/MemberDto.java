package jpabook.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
@Data

public class MemberDto {
    private String username;
    private int age;

    public MemberDto() {
    }

    // 아키텍쳐적인 고민.
    @QueryProjection
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}