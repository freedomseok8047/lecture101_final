package com.lecture101.controller;

import com.lecture101.dto.ItemSearchDto;
import com.lecture101.dto.MainItemDto;
import com.lecture101.dto.MainMemberDto;
import com.lecture101.dto.MemberSearchDto;
import com.lecture101.service.ItemService;
import com.lecture101.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping(value = "/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
//    @GetMapping(value = "/")
//    public String main(MemberSearchDto memberSearchDto, Optional<Integer> page, Model model){
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
//        Page<MainMemberDto> members = memberService.getMainMemberPage(memberSearchDto, pageable);
//
//        model.addAttribute("members", members);
//        model.addAttribute("memberSearchDto", memberSearchDto);
//        model.addAttribute("maxPage", 5);
//
//        return "main";
//    }

}