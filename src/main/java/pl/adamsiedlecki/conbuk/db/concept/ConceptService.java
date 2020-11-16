package pl.adamsiedlecki.conbuk.db.concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptService {

    private final ConceptRepo conceptRepo;

    @Autowired
    public ConceptService(ConceptRepo conceptRepo) {
        this.conceptRepo = conceptRepo;
    }

    public boolean saveConcept(Concept concept) {
        conceptRepo.saveAndFlush(concept);
        return true;
    }

    public Long count() {
        return conceptRepo.count();
    }

    public boolean deleteConceptById(Long id) {
        conceptRepo.deleteById(id);
        return true;
    }
}
