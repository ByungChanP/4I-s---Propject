package net.likelion.bebc25.first_project.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 회원 정보 데이터를 전달하기 위한 데이터 객체(DTO)입니다.
 * 유효성 검증을 위한 어노테이션이 적용되어 있습니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
    /**
     * 회원 일련번호
     */
    private int id;

    /**
     * 회원 로그인 아이디 -> 이메일로 통합
     */
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    /**
     * 회원 로그인 비밀번호
     */
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하여야 합니다.")
    private String password;

    @NotBlank
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    private String nickname;

    /**
     * 회원 가입 일시
     */
    private LocalDateTime createdAt;

    /**
     * 프로필 이미지 저장경로
     */
    private String profileImgDir;
}
