package com.lecture101.dto;


import com.lecture101.constant.Category;
import com.lecture101.constant.ItemSellStatus;
import com.lecture101.constant.LectureType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus; // 클래스 상태 검색 (강의중, 강의종료)

    private LectureType searchTypeStatus; // 클래스 타입 검색 (1:1, 그룹, 온라이)

    private Category searchCategory; // 클래스 카테고리 검색 (미술, 댄스, 스포츠...)

    private String searchBy;

    private String searchQuery = "";

}