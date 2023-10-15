package com.lecture101.controller;

import com.lecture101.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/selectedDate/{cartItemId}")
    public ResponseEntity<String> getSelectedDate(@PathVariable Long cartItemId) {
        try {
            // CartItemService에서 cartItemId를 사용하여 selectedDate를 가져옵니다.
            String selectedDate = cartItemService.getSelectedDate(cartItemId);
            return new ResponseEntity<>(selectedDate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}






