package me.dio.gatomia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import me.dio.gatomia.model.Owner;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class OwnerDto {
    private final Long ownerId;
    @NonNull
    @Size(min = 1)
    private final String ownerName;

    public OwnerDto(Owner owner) {
        this.ownerId = owner.getId();
        this.ownerName = owner.getName();
    }
}
