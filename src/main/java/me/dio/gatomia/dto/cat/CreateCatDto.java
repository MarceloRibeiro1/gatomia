package me.dio.gatomia.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.dio.gatomia.enumeration.BehaviorType;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NonNull
@NoArgsConstructor
public class CreateCatDto {
    @Size(min = 1)
    private String catName;
    private BehaviorType behavior;
    private Long ownerId;

}
