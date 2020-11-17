package pl.adamsiedlecki.conbuk.db.concept;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptRepo extends JpaRepository<Concept, Long> {

    @Query("SELECT c FROM Concept c")
    List<Concept> findTop(Pageable pageable);
}
