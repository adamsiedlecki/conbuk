package pl.adamsiedlecki.conbuk.db.concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.conbuk.db.user.UserDs;

import java.util.List;

@Service
public class ConceptService {

    private final ConceptRepo conceptRepo;
    private final UserDs userDs;

    @Autowired
    public ConceptService(ConceptRepo conceptRepo, UserDs userDs) {
        this.conceptRepo = conceptRepo;
        this.userDs = userDs;

        Concept c = new Concept();
        c.setAuthor(userDs.getUserByUsername("admin").get());
        c.setName("face*ook but without spying");
        conceptRepo.saveAndFlush(c);

        Concept c2 = new Concept();
        c2.setAuthor(userDs.getUserByUsername("admin").get());
        c2.setName("country but without taxes");
        conceptRepo.saveAndFlush(c2);
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

    public List<Concept> getTop100() {
        Pageable pageable = PageRequest.of(0, 100);
        return conceptRepo.findTop(pageable);
    }
}
