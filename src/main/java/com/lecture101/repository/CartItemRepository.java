package com.lecture101.repository;

import com.lecture101.dto.CartDetailDto;
import com.lecture101.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    //날짜/장바구니 목록 구현 코드
    @Query("select new com.lecture101.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl, FUNCTION('DATE_FORMAT', ci.selectedDate, '%Y-%m-%d')) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
            )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
    List<CartItem> findBySelectedDate(LocalDate selectedDate);

}