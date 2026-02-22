import java.util.ArrayList;
import java.util.List;

public class Demo03 {

    public static void main(String[] args) {
        StudentProfile student = new StudentProfile(
                "23BCS1001",
                "Ayaan",
                8.10,
                72,
                18,
                LegacyFlags.NONE
        );
        List<EligibilityRule> rules = new ArrayList<>();
        rules.add(new CgrEligibilityRule(7.0));
        rules.add(new AttendanceEligibilityRule(75));
        rules.add(new CreditsEligibilityRule(20));
        rules.add(new DisciplinaryEligibilityRule());

        EligibilityEngine engine =
                new EligibilityEngine(rules, new FakeEligibilityStore());

        EvaluationResult result = engine.evaluate(student);

        System.out.println("=== Placement Eligibility ===");
        new ReportPrinter().print(student, result);
    }
}