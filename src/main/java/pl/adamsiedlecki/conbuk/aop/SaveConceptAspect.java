package pl.adamsiedlecki.conbuk.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.adamsiedlecki.conbuk.apiCalls.CelebrateAddConcept;

@Component
@Aspect
public class SaveConceptAspect {

    private final static int BREAK_TIME_MILLIS = 1500;
    private static long lastCelebrationTime = 0;

    @After("execution(* pl.adamsiedlecki.conbuk.db.concept.ConceptService.saveConcept(..))")
    public void afterConceptSave() {
        long now = System.currentTimeMillis();
        if (now - lastCelebrationTime > BREAK_TIME_MILLIS) {
            System.out.println("AFTER CONCEPT SAVED");
            CelebrateAddConcept celebrateAddConcept = new CelebrateAddConcept();
            celebrateAddConcept.start();
            lastCelebrationTime = now;
        }

    }
}
