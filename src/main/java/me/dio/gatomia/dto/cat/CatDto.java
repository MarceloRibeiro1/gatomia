package me.dio.gatomia.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.model.Cats;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NonNull
@NoArgsConstructor
public class CatDto {
    private Long catId;
    @Size(min = 1)
    private String catName;
    private BehaviorType behavior;
    private Long ownerId;

    public CatDto(Cats cat) {
        this.catId = cat.getId();
        this.catName = cat.getName();
        this.behavior = cat.getBehavior();
        this.ownerId = cat.getOwner().getId();
    }
}
