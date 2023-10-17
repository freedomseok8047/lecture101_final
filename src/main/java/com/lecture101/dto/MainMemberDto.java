package com.lecture101.dto;

import com.lecture101.constant.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMemberDto {
    private Long id;

    private Role role;

    private String name;


    @QueryProjection
    public MainMemberDto(Long id, Role role, String name){
        this.id = id;
        this.role = role;
        this.name = name;

    }

}
