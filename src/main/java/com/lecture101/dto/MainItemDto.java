package com.lecture101.dto;


import com.lecture101.constant.Category;
import com.lecture101.constant.LectureType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;

    // 카테고리 추가
    private Category category;

    private LectureType lectureType;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, Category category, LectureType lectureType, String itemNm, String itemDetail, String imgUrl,Integer price){
        this.id = id;
        this.category = category;
        this.lectureType = lectureType;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}