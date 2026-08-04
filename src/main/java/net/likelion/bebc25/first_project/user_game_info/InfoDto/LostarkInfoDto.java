package net.likelion.bebc25.first_project.user_game_info.InfoDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LostarkInfoDto {
    /**
     * 일련의 회원 번호
     */
    private int id;

    /**
     * 게임식별자 id
     */
    @NotNull
    private int game_id;

    /**
     * 회원 식별자 id
     */
    @NotNull
    private int member_id;


    @NotNull
    private LostarkIngameInfoDto lostarkInfoDto;
}
