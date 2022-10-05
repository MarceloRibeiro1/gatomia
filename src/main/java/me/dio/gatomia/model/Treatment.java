package me.dio.gatomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.dio.gatomia.enumeration.MeowType;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppInvalidModelException;

import javax.persistence.*;

@Entity
@Table(name = "treatment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treatment_id")
    private Long id;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Cats cat;

    @Enumerated
    @Column(name = "meow_type")
    private MeowType meow;

    @Builder.Default
    private boolean isTreated = false;


    public Solutions treat() {
        if (isTreated) {
            throw new AppInvalidModelException("Cat already treated");
        }
        if (this.meow == null) {
            throw new AppInvalidModelException("Cat meow is required");
        }
        if (this.cat == null) {
            throw new AppInvalidModelException("Cat is required");
        }
        int solution = (this.meow.ordinal() + this.cat.getBehavior().ordinal()) % Solutions.values().length;
        setTreated(true);
        return Solutions.values()[solution];
    }

    public Solutions getTreat() {
        if (this.meow == null) {
            throw new AppInvalidModelException("Cat meow is required");
        }
        if (this.cat == null) {
            throw new AppInvalidModelException("Cat is required");
        }
        int solution = (this.meow.ordinal() + this.cat.getBehavior().ordinal()) % Solutions.values().length;
        return Solutions.values()[solution];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if (owner == null) throw new AppInvalidModelException("Owner must not be null");
        this.owner = owner;
    }

    public Cats getCat() {
        return cat;
    }

    public void setCat(Cats cat) {
        if (cat == null) throw new AppInvalidModelException("Cat must not be null");
        if (cat.getBehavior() == null) throw new AppInvalidModelException("Invalid cat: " + cat.getId());
        this.cat = cat;
    }

    public MeowType getMeow() {
        return meow;
    }

    public void setMeow(MeowType meow) {
        if (meow == null) throw new AppInvalidModelException("Meow must not be null");
        this.meow = meow;
    }

    public boolean isTreated() {
        return isTreated;
    }

    public void setTreated(boolean treated) {
        isTreated = treated;
    }
}
