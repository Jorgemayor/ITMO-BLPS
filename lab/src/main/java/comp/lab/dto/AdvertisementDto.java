package comp.lab.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AdvertisementDto {
    @NotNull(message = "must exist")
    @Size(min = 4, max = 255, message = "length must be from 4 to 255 char long")
    private final String name;

    @NotNull(message = "must exist")
    private final Integer price;

    @NotNull(message = "must exist")
    @Size(min = 4, max = 255, message = "length must be from 4 to 255 char long")
    private final String description;

    @NotNull(message = "must exist")
    @Size(min = 4, max = 255, message = "length must be from 4 to 255 char long")
    private final String regionName;

    @NotNull(message = "must exist")
    @Size(min = 4, max = 255, message = "length must be from 4 to 255 char long")
    private final String cityName;

    @NotNull(message = "must exist")
    @Size(min = 4, max = 255, message = "length must be from 4 to 255 char long")
    private final String sectionName;
}
