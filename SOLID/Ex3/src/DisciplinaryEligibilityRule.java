public class DisciplinaryEligibilityRule implements EligibilityRule {

    @Override
    public String validate(StudentProfile student) {
        if (student.getDisciplinaryFlag() != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}