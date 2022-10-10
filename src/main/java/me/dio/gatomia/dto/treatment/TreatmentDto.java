package me.dio.gatomia.dto.treatment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.model.Treatment;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TreatmentDto {
    @NotNull
    private Long treatmentId;
    @NotNull
    private MeowType type;
    @NotNull
    private Long ownerId;
    @NotNull
    private Long catId;

    public TreatmentDto(Treatment treatment) {
        this.treatmentId = treatment.getId();
        this.ownerId = treatment.getOwner().getId();
        this.catId = treatment.getCat().getId();
        this.type = treatment.getMeow();
    }
}
