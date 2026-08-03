package net.likelion.bebc25.first_project.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
// 게시글 하나를 저장할 객체
public class PostDto {
    private int id;

    private int gameId;

    private String author;

    private String tag;

    private String minRank;

    private String maxRank;

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해야 합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private int participantCount;

    @NotBlank(message = "모집인원을 설정해주세요.")
    private int maxParticipantCount;

    @NotBlank(message = "마감 시간을 설정해주세요.")
    private LocalDateTime deadline;

    private LocalDateTime createdAt;
}