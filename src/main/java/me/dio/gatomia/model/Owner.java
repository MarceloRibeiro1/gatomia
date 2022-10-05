package me.dio.gatomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dio.gatomia.handler.AppInvalidModelException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owner")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "owner_id")
    private Long id;

    @Column(name = "owner_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cats> cats;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Treatment> TreatedCats;

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
        if (name == null || name.isEmpty()) throw new AppInvalidModelException("Name is required");
        this.name = name;
    }

    public List<Cats> getCats() {
        return cats;
    }

    public void setCats(List<Cats> cats) {
        if (cats == null) throw new AppInvalidModelException("Cats is required");
        this.cats = cats;
    }

    public List<Treatment> getTreatedCats() {
        return TreatedCats;
    }

    public void setTreatedCats(List<Treatment> treatedCats) {
        if (treatedCats == null) throw new AppInvalidModelException("TreatedCats is required");
        this.TreatedCats = treatedCats;
    }
}
