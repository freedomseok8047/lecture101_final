package com.lecture101.dto;

import com.lecture101.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
        // 날짜/주문 관련 코드
        this.selectedDate = orderItem.getSelectedDate();
    }

    private String itemNm; //상품명
    private int count; //주문 수량

    private int orderPrice; //주문 금액
    private String imgUrl; //상품 이미지 경로
    // 날짜/주문 관련 코드
    private LocalDate selectedDate; // 상품 선택 날짜

}