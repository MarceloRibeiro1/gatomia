package me.dio.gatomia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.model.Cats;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@Builder
public class CatDto {
    private final Long catId;
    @NonNull
    @Size(min = 1)
    private final String catName;
    @NonNull
    private final BehaviorType behavior;
    @NonNull
    private final Long ownerId;

    public CatDto(Cats cat) {
        this.catId = cat.getId();
        this.catName = cat.getName();
        this.behavior = cat.getBehavior();
        this.ownerId = cat.getOwner().getId();
    }
}
