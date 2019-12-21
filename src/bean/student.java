package bean;

public class student {

    private String studentNo;
    private int instituteNo;
    private String dormitoryNo;
    private String studentName;
    private String studentSex;
    private String studentGrade;
    private String studentTel;

    public String getStudentNo() {
        return studentNo;
    }

    public int getInstituteNo() {
        return instituteNo;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public String getStudentTel() {
        return studentTel;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setInstituteNo(int instituteNo) {
        this.instituteNo = instituteNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel;
    }
}
