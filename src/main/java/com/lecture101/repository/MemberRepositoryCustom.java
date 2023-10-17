package com.lecture101.repository;

import com.lecture101.dto.ItemSearchDto;
import com.lecture101.dto.MainItemDto;
import com.lecture101.dto.MainMemberDto;
import com.lecture101.dto.MemberSearchDto;
import com.lecture101.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);

//    Page<MainMemberDto> getMainMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);

}
