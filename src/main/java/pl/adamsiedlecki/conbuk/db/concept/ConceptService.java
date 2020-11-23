package pl.adamsiedlecki.conbuk.db.concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.conbuk.db.user.UserDs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        c.setSaveTime(LocalDateTime.now());
        conceptRepo.saveAndFlush(c);

        Concept c2 = new Concept();
        c2.setAuthor(userDs.getUserByUsername("admin").get());
        c2.setName("country but without taxes");
        c2.getLikeUsers().add(userDs.getUserByUsername("admin").get());
        c2.getLikeUsers().add(userDs.getUserByUsername("user").get());
        c2.setSaveTime(LocalDateTime.now());
        conceptRepo.saveAndFlush(c2);

        Concept c3 = new Concept();
        c3.setSaveTime(LocalDateTime.now());
        c3.setAuthor(userDs.getUserByUsername("admin").get());
        c3.setName("school but without marks");
        c3.getLikeUsers().add(userDs.getUserByUsername("admin").get());
        conceptRepo.saveAndFlush(c3);

//        for (int i = 1; i <= 100; i++) {
//            Concept c4 = new Concept();
//            c4.setAuthor(userDs.getUserByUsername("admin").get());
//            c4.setName("test concept nr: " + i);
//            conceptRepo.saveAndFlush(c4);
//        }
    }

    public boolean saveNewConcept(Concept concept) {
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

    public List<Concept> getNewest() {
        Pageable pageable = PageRequest.of(0, 100);
        return conceptRepo.find100Newest(pageable);
    }

    public List<Concept> findAll() {
        return conceptRepo.findAll();
    }

    public Optional<Concept> getConceptByName(String name) {
        return conceptRepo.getConceptByName(name);
    }

    public void flush() {
        conceptRepo.flush();
    }

    public void deleteConcept(Concept c) {
        conceptRepo.delete(c);
    }
}
