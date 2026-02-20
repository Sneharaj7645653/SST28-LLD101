import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentValidator {
    ProgramList programList;
    StudentValidator(ProgramList programList) {
        this.programList = programList;
    }
    public List<String> validate(Map<String,String> kv) {

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors = new ArrayList<>();
        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!programList.validProgram(program)) errors.add("program is invalid");

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return errors;
        }

        return null;
    }
}
