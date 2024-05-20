package comp.lab.controllers;

import comp.lab.model.*;
import comp.lab.security.UserPrincipal;
import comp.lab.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<Advertisement>> getAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAdvertisements());
    }

    @GetMapping(path = "/region/{regionName}")
    public ResponseEntity<List<Advertisement>> findAdvertisementsByRegionName(@PathVariable("regionName") String regionName) {
        return ResponseEntity.ok(advertisementService.findAdvertisementsByRegionName(regionName));
    }

    @GetMapping(path = "/section/{sectionName}")
    public ResponseEntity<List<Advertisement>> findAdvertisementsBySectionName(@PathVariable("sectionName") String sectionName) {
        return ResponseEntity.ok(advertisementService.findAdvertisementsBySectionName(sectionName));
    }

    @GetMapping(path = "/{idAdvertisement}")
    public ResponseEntity<Advertisement> findAdvertisementsById(@PathVariable("idAdvertisement") Long idAdvertisement) {
        return ResponseEntity.ok(advertisementService.findAdvertisementById(idAdvertisement));
    }

    @PostMapping
    public ResponseEntity<String> addAdvertisement(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String description,
            @RequestParam String regionName,
            @RequestParam String cityName,
            @RequestParam String sectionName
            ) {
        advertisementService.addNewAdvertisementWithEmail(
                principal,
                name,
                price,
                description,
                regionName,
                cityName,
                sectionName
        );
        return new ResponseEntity<>("Add created successfully!", HttpStatus.CREATED);
    }
}
