package me.dio.gatomia.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.model.Cats;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
    @NotNull
    private Long catId;
    @Size(min = 2)
    @NotNull
    private String catName;
    @NotNull
    private BehaviorType behavior;
    @NotNull
    private Long ownerId;

    public CatDto(Cats cat) {
        this.catId = cat.getId();
        this.catName = cat.getName();
        this.behavior = cat.getBehavior();
        this.ownerId = cat.getOwner().getId();
    }
}
