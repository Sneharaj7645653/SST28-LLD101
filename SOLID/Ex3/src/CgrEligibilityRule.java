public class CgrEligibilityRule implements EligibilityRule {

    private final double minCgr;

    public CgrEligibilityRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public String validate(StudentProfile student) {
        if (student.getCgr() < minCgr) {
            return "CGR below " + minCgr;
        }
        return null;
    }
}