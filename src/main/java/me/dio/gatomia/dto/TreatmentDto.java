package me.dio.gatomia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.model.Treatment;

@AllArgsConstructor
@Data
@Builder
public class TreatmentDto {
    private final Long treatmentId;
    @NonNull
    private final MeowType type;
    @NonNull
    private final Long ownerId;
    @NonNull
    private final Long catId;

    public TreatmentDto(Treatment treatment) {
        this.treatmentId = treatment.getId();
        this.ownerId = treatment.getOwner().getId();
        this.catId = treatment.getCat().getId();
        this.type = treatment.getMeow();
    }
}
