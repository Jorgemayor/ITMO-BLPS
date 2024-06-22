package comp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "region",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Region {

    @Id
    @SequenceGenerator(
            name = "region_sequence",
            sequenceName = "region_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "region_sequence"
    )
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<City> cities;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Advertisement> advertisements;

    public Region(String name) {
        this.name = name;
    }
}