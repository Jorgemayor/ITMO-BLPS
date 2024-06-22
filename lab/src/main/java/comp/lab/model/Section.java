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
@Table(name = "section",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Section {
    @Id
    @SequenceGenerator(
            name = "section_sequence",
            sequenceName = "section_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "section_sequence"
    )
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Advertisement> advertisements;

    public Section(String name) {
        this.name = name;
    }
}
