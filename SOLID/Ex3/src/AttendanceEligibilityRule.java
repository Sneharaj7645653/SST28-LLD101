public class AttendanceEligibilityRule implements EligibilityRule {

    private final int minAttendance;

    public AttendanceEligibilityRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public String validate(StudentProfile student) {
        if (student.getAttendancePct() < minAttendance) {
            return "attendance below " + minAttendance;
        }
        return null;
    }
}