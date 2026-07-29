package net.likelion.bebc25.first_project.game.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GameDto {

    private int id;

    private String gameTitle;

    private String details;

    private String logoImgDir;

    private String backgroundImgDir;
}
