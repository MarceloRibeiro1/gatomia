package me.dio.gatomia.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.dio.gatomia.model.Owner;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NonNull
@NoArgsConstructor
public class OwnerDto {
    private Long ownerId;
    @Size(min = 1)
    private String ownerName;

    public OwnerDto(Owner owner) {
        this.ownerId = owner.getId();
        this.ownerName = owner.getName();
    }
}
