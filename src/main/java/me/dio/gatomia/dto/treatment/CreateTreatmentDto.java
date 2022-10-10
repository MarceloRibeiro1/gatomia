package me.dio.gatomia.dto.treatment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.MeowType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CreateTreatmentDto {
    @NotNull
    private MeowType type;
    @NotNull
    private Long ownerId;
    @NotNull
    private Long catId;
}
