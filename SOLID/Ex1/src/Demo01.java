public class Demo01 {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        ProgramList pl = new ProgramList();
        pl.addProgram("CSE");
        Database db = new FakeDb();
        StudentParser sp =new StudentParser();
        StudentValidator sv = new StudentValidator(pl);
        ConsoleOnboardingReporter cor = new ConsoleOnboardingReporter();
        OnboardingService svc = new OnboardingService(db, sp, sv, cor);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));


        // Example that fails validation
        String raw2 = "name=Riya;email=riyasst.edu;phone=9876543210;program=ECE";
        svc.registerFromRawInput(raw2);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
