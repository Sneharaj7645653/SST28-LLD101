import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class EligibilityEngine {

    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules,
                             FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public EvaluationResult evaluate(StudentProfile student) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            String result = rule.validate(student);
            if (result != null) {
                reasons.add(result);
            }
        }

        boolean eligible = reasons.isEmpty();

        store.save(student.getRollNo());

        return new EvaluationResult(eligible, reasons);
    }
}

