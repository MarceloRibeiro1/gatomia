package me.dio.gatomia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.BehaviorType;
import me.dio.gatomia.handler.AppInvalidModelException;

import javax.persistence.*;

@Entity
@Table(name = "cats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    private Long id;

    @Column(name = "cat_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Owner owner;

    @Enumerated
    private BehaviorType behavior;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new AppInvalidModelException("Name must not be empty");
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if (owner == null) throw new AppInvalidModelException("Owner must not be null");
        this.owner = owner;
    }

    public BehaviorType getBehavior() {
        return behavior;
    }

    public void setBehavior(BehaviorType behavior) {
        if (behavior == null) throw new AppInvalidModelException("Behavior must not be null");
        this.behavior = behavior;
    }
}
