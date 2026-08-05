package net.likelion.bebc25.first_project.user_game_info.InfoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class LolIngameInfoDto implements IngameInfoDto {
    @NotBlank
    private String nickname;
    @NotNull
    private int level;
    @NotBlank
    private String rank;
    @NotEmpty
    private List<String> position;
}
