package net.likelion.bebc25.first_project.user_game_info.InfoDto;

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
@NotNull
public class IngameInfoDto {
    private String nickname;
    private int level;
    private String rank;
    private List<String> position;
}
