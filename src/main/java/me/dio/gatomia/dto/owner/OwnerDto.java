package me.dio.gatomia.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dio.gatomia.model.Owner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    @NotNull
    private Long ownerId;
    @Size(min = 2)
    @NotNull
    private String ownerName;

    public OwnerDto(Owner owner) {
        this.ownerId = owner.getId();
        this.ownerName = owner.getName();
    }
}
