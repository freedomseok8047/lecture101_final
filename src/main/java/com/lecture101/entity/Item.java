package com.lecture101.entity;


import com.lecture101.constant.Category;
import com.lecture101.constant.ItemSellStatus;
import com.lecture101.constant.LectureType;
import com.lecture101.dto.ItemFormDto;
import com.lecture101.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       // 클래스 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //클래스명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //인원수

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 강의 상태

    @Enumerated(EnumType.STRING)
    private LectureType lectureType; // 클래스 타입

    @Enumerated(EnumType.STRING)
    private Category category; // 카테고리판매 상태

    //날짜/시간 추가한 작업 시작 부분
    @Column(name = "class_start_date")
    private LocalDate classStartDate;

    @Column(name = "class_end_date")
    private LocalDate classEndDate;
    //날짜/시간 추가한 작업 끝 부분
    /*현석 파트 끝*/

    // 1011 ktb
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();

        // 클래스 타입
        this.lectureType = itemFormDto.getLectureType();
        // 카테고리
        this.category = itemFormDto.getCategory();

        //날짜/시간 추가한 작업 시작 부분
        this.classStartDate = itemFormDto.getClassStartDateAsLocalDate();
        this.classEndDate = itemFormDto.getClassEndDateAsLocalDate();
        //날짜/시간 추가한 작업 끝 부분
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}