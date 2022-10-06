package me.dio.gatomia.dto.treatment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.dio.gatomia.enumeration.MeowType;

@AllArgsConstructor
@Getter
@NonNull
@NoArgsConstructor
public class CreateTreatmentDto {
    private MeowType type;
    private Long ownerId;
    private Long catId;
}
