package net.likelion.bebc25.first_project.user_game_info.InfoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LostarkIngameInfoDto {
    @NotBlank
    private String nickname;
    @NotNull
    private int equipLevel;
    @NotBlank
    private String job;

}
