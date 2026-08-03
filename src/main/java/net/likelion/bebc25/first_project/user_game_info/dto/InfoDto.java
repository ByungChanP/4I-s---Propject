package net.likelion.bebc25.first_project.user_game_info.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 인게임 프로필 정보를 저장하고 전달하기 위한 DTO입니다
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class InfoDto {
    /**
     * 일련의 회원 번호
     */
    private int id;

    /**
     * 게임식별자 id
     */
    @NotNull
    private int gameId;

    /**
     * 회원 식별자 id
     */
    @NotNull
    private int memberId;


    private String ingameInfo;
}
