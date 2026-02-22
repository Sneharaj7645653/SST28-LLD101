public class CreditsEligibilityRule implements EligibilityRule {

    private final int minCredits;

    public CreditsEligibilityRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public String validate(StudentProfile student) {
        if (student.getEarnedCredits() < minCredits) {
            return "credits below " + minCredits;
        }
        return null;
    }
}