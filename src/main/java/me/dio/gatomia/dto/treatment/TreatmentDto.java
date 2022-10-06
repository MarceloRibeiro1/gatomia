package me.dio.gatomia.dto.treatment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.model.Treatment;

@AllArgsConstructor
@Getter
@NonNull
@NoArgsConstructor
public class TreatmentDto {
    private Long treatmentId;
    private MeowType type;
    private Long ownerId;
    private Long catId;

    public TreatmentDto(Treatment treatment) {
        this.treatmentId = treatment.getId();
        this.ownerId = treatment.getOwner().getId();
        this.catId = treatment.getCat().getId();
        this.type = treatment.getMeow();
    }
}
