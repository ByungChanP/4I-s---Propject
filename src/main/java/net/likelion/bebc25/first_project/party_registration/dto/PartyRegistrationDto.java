package net.likelion.bebc25.first_project.party_registration.dto;

import lombok.*;

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

    private String author;

    private int content;

    private LocalDateTime createdAt;
}