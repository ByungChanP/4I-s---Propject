package net.likelion.bebc25.first_project.party_registration.dto;

import lombok.*;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
// 게시글 하나를 저장할 객체
public class PartyRegistrationDto {
    private int id;

    private int postId;

    private int memberId;

    private String memberNickname;

    private String participantInfoString;

    private LocalDateTime createdAt;

    private IngameInfoDto participantInfo;

    private String position;
}
