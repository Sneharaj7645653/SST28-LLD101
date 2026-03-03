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




/**
 * The Open-Closed Principle (OCP) is maintained here by shifting the responsibility of
 * defining rules away from the EligibilityEngine and into a pluggable list of
 * EligibilityRule objects. In the old code, the engine was "closed" to new
 * rules because you had to physically edit its source code and add if-else
 * blocks to change behavior. In the new version, the evaluate method is
 * generic—it simply loops through a list and asks each rule for its opinion.
 * This makes the engine Closed for Modification (you never have to touch the
 * EligibilityEngine file again) but Open for Extension (you can add a "Library Fine Rule" or a
 * "Project Submission Rule" just by creating a new class and passing it into the constructor).
 *
 * The Flow of the Refactored Solution
 * The new flow follows a "Collection-based Evaluation" pattern where
 * the engine acts as a coordinator rather than a decision-maker:
 *
 * Initialization: The Main method (or a configuration layer) creates a list
 * of specific rule objects (e.g., AttendanceRule, GpaRule) and "injects" them into the EligibilityEngine constructor.
 *
 * Trigger: The runAndPrint(StudentProfile) method is called to
 * start the process for a specific student.
 *
 * Iteration: The evaluate method starts with an empty list of reasons.
 * It then iterates through the rules list.
 *
 * Individual Assessment: For every rule in the list, the engine calls rule.evaluate(s).
 *
 * If the student passes the rule, the rule returns null.
 *
 * If the student fails (e.g., GPA is too low), the rule returns a
 * specific String describing the failure.
 *
 * Aggregation: The engine collects all non-null strings into the reasons list.
 *
 * Decision: After the loop, the engine checks if reasons is empty. If
 * it is, the status is set to "ELIGIBLE"; otherwise, it's "NOT_ELIGIBLE".
 *
 * Finalization: The EligibilityEngineResult is created, printed by the ReportPrinter, and saved to the FakeEligibilityStore.*/