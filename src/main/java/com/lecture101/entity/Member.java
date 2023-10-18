package com.lecture101.entity;

import com.lecture101.constant.Role;
import com.lecture101.dto.MemberFormDto;
import com.lecture101.dto.MemberUpdateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom2-id")
    @GenericGenerator(name = "custom2-id", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "lecture101"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "20230001"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

    // Member ENtity 클래스 내부에 정의된
    // MemberUpdateDto와 PasswordEncoder를 파라미터로 하는
    // updateMember 메서드
    public void updateMember(MemberUpdateDto memberUpdateDto, PasswordEncoder passwordEncoder) {
        // Dto로 부터 넘겨받은 정보들을 각각 매핑하여 reset한다.
        this.setName(memberUpdateDto.getName());
        this.setEmail(memberUpdateDto.getEmail());
        this.setAddress(memberUpdateDto.getAddress());
        // controller에서 인코딩해서 정보를 넘긴것이 아니라
        // 그대로 숫자형태로 entity 클래스까지 넘기고
        // 여기서 인코딩해서 Set!
        String password = passwordEncoder.encode(memberUpdateDto.getNewPassword());
        this.setPassword(password);

        // 업데이트 완료!!
    }


}
//MemberUpdateDto에 이미   @NotBlank/@NotEmpty 로
// 유효성 검사 걸어서 null체크 필요없음

//        if (memberUpdateDto.getName() != null) {
//            this.setName(memberUpdateDto.getName());
//        }
//        if (memberUpdateDto.getEmail() != null) {
//            this.setEmail(memberUpdateDto.getEmail());
//        }
//        if (memberUpdateDto.getAddress() != null) {
//            this.setAddress(memberUpdateDto.getAddress());
//        }
//        if (memberUpdateDto.getNewPassword() != null) {
//            String password = passwordEncoder.encode(memberUpdateDto.getNewPassword());
//            this.setPassword(password);
//        }