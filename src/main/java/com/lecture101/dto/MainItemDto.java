package com.lecture101.dto;


import com.lecture101.constant.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;

    // 카테고리 추가
    private Category category;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, Category category, String itemNm, String itemDetail, String imgUrl,Integer price){
        this.id = id;
        this.category = category;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}