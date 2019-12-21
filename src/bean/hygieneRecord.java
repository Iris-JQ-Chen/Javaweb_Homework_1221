package bean;

import java.util.Date;

public class hygieneRecord {

    private int hygieneId;
    private String managerNo;
    private String dormitoryNo;
    private String hygieneGrade;
    private Date recordDate;

    public String getManagerNo() {
        return managerNo;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public int getHygieneId() {
        return hygieneId;
    }

    public String getHygieneGrade() {
        return hygieneGrade;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public void setHygieneGrade(String hygieneGrade) {
        this.hygieneGrade = hygieneGrade;
    }

    public void setHygieneId(int hygieneId) {
        this.hygieneId = hygieneId;
    }
}
