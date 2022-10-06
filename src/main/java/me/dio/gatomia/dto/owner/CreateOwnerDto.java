package me.dio.gatomia.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NonNull
@NoArgsConstructor
public class CreateOwnerDto {
    @Size(min = 1)
    private String ownerName;
}
