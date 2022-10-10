package me.dio.gatomia.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.BehaviorType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatDto {
    @Size(min = 2)
    @NotNull
    private String catName;
    @NotNull
    private BehaviorType behavior;
    @NotNull
    private Long ownerId;

}
