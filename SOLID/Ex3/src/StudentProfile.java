public class StudentProfile {
    public final String rollNo;
    public final String name;
    public final double cgr;
    public final int attendancePct;
    public final int earnedCredits;
    public final int disciplinaryFlag;

    public String getRollNo() {
        return rollNo;
    }

    public int getDisciplinaryFlag() {
        return disciplinaryFlag;
    }

    public int getEarnedCredits() {
        return earnedCredits;
    }

    public double getCgr() {
        return cgr;
    }

    public String getName() {
        return name;
    }

    public int getAttendancePct() {
        return attendancePct;
    }

    public StudentProfile(String rollNo, String name, double cgr, int attendancePct, int earnedCredits, int disciplinaryFlag) {
        this.rollNo = rollNo; this.name = name; this.cgr = cgr;
        this.attendancePct = attendancePct; this.earnedCredits = earnedCredits;
        this.disciplinaryFlag = disciplinaryFlag;
    }
}
