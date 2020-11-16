package pl.adamsiedlecki.conbuk.db.concept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepo extends JpaRepository<Concept, Long> {
}
