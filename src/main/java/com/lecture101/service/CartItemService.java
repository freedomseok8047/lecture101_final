package com.lecture101.service;

import com.lecture101.entity.CartItem;
import com.lecture101.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public String getSelectedDate(Long cartItemId) {
        // cartItemId를 사용하여 CartItem 엔티티를 가져온 후, 그 안에서 selectedDate를 추출합니다.
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        return cartItem.getSelectedDate().toString(); // LocalDate를 문자열로 변환
    }
}
