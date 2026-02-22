public class ReportPrinter {
    public void print(StudentProfile s, EvaluationResult r) {
        System.out.println("Student: " + s.name + " (CGR=" + String.format("%.2f", s.cgr)
                + ", attendance=" + s.attendancePct + ", credits=" + s.earnedCredits
                + ", flag=" + LegacyFlags.nameOf(s.disciplinaryFlag) + ")");
        System.out.println("RESULT: " + r.isEligible());System.out.println("RESULT: " +
                (r.isEligible() ? "ELIGIBLE" : "NOT_ELIGIBLE"));
        for (String reason : r.getReasons()) System.out.println("- " + reason);
    }
}
