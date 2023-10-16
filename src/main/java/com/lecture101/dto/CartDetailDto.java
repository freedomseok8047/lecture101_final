package com.lecture101.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemNm; //상품명

    private int price; //상품 금액

    private int count; //수량

    private String imgUrl; //상품 이미지 경로

    //날짜/장바구니 목록 구현 코드
    private String selectedDate; //상품 선택 날짜

    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, String imgUrl, String selectedDate){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
        this.selectedDate = selectedDate;
    }

}