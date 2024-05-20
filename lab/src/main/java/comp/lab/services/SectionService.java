package comp.lab.services;

import comp.lab.model.Section;
import comp.lab.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Optional<Section> findSectionByName(String name) {
        return sectionRepository.findByName(name);
    }
}
