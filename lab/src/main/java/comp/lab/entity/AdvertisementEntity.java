package comp.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "advertisement")
public class AdvertisementEntity {
    @Id
    @SequenceGenerator(
            name = "advertisement_sequence",
            sequenceName = "advertisement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "advertisement_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity user;

    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Integer price;

    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private RegionEntity region;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity city;

    private byte[] image;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private SectionEntity section;

    public AdvertisementEntity(UserEntity userEntity, String name, Integer price, String description, RegionEntity region, CityEntity city, SectionEntity section) {
        this.status = Status.REVISION;
        this.user = userEntity;
        this.name = name;
        this.price = price;
        this.description = description;
        this.region = region;
        this.city = city;
        this.section = section;
    }
}
