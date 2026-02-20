import java.util.*;

public class OnboardingService {
    private final Database db;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final ConsoleOnboardingReporter reporter;

    public OnboardingService(Database db, StudentParser parser, StudentValidator validator, ConsoleOnboardingReporter reporter) {
        this.db = db;
        this.parser = parser;
        this.validator = validator;
        this.reporter = reporter;
    }

    public void registerFromRawInput(String raw) {
        reporter.printInput(raw);

        Map<String,String> kv = parser.parse(raw);
        List<String> errors = validator.validate(kv);
        if (errors != null) return;

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");


        String id = IdUtil.nextStudentId(db.count());


        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        reporter.printSuccess(rec, db.count());
    }
}
