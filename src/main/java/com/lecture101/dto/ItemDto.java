package com.lecture101.dto;

import com.lecture101.constant.LectureType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private LectureType lectureType; // 클래스 타입

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}