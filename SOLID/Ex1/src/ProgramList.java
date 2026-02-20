import java.util.ArrayList;

public class ProgramList {
    ArrayList<String> programs = new ArrayList<>();

    public void addProgram(String program) {
        programs.add(program);
    }

    public boolean validProgram(String p) {
        return programs.contains(p);
    }

}
