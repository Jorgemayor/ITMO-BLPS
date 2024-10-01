package comp.lab.controllers;

import comp.lab.dto.AdvertisementDto;
import comp.lab.entity.*;
import comp.lab.security.UserPrincipal;
import comp.lab.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/add")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public ResponseEntity<List<AdvertisementEntity>> getAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAdvertisements());
    }

    @GetMapping(path = "/region/{regionName}")
    public ResponseEntity<List<AdvertisementEntity>> findAdvertisementsByRegionName(@PathVariable("regionName") String regionName) {
        return ResponseEntity.ok(advertisementService.findAdvertisementsByRegionName(regionName));
    }

    @GetMapping(path = "/section/{sectionName}")
    public ResponseEntity<List<AdvertisementEntity>> findAdvertisementsBySectionName(@PathVariable("sectionName") String sectionName) {
        return ResponseEntity.ok(advertisementService.findAdvertisementsBySectionName(sectionName));
    }

    @GetMapping(path = "/{idAdvertisement}")
    public ResponseEntity<AdvertisementEntity> findAdvertisementsById(@PathVariable("idAdvertisement") Long idAdvertisement) {
        return ResponseEntity.ok(advertisementService.findAdvertisementById(idAdvertisement));
    }

    @PostMapping
    public ResponseEntity<String> addAdvertisement(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody @Validated AdvertisementDto advertisementDto
            ) {
        advertisementService.addNewAdvertisementWithEmail(
                principal.getEmail(),
                advertisementDto
        );
        return new ResponseEntity<>("Add created successfully!", HttpStatus.CREATED);
    }
}
