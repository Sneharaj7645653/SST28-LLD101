public class ConsoleOnboardingReporter  {

    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printSuccess(StudentRecord rec, int total) {
        System.out.println("OK: created student " + rec.getId());
        System.out.println("Saved. Total students: " + total);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}