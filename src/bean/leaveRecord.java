package bean;

import java.util.Date;

public class leaveRecord {

    private int leaveId;
    private String studentNo;
    private String place;
    private String reason;
    private Date leaveDate;
    private Date exbackDate;
    private Date acbackDate;
    private int isAprove;

    public Date getAcbackDate() {
        return acbackDate;
    }

    public Date getExbackDate() {
        return exbackDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public String getPlace() {
        return place;
    }

    public String getReason() {
        return reason;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public int getIsAprove() {
        return isAprove;
    }

    public void setAcbackDate(Date acbackDate) {
        this.acbackDate = acbackDate;
    }

    public void setExbackDate(Date exbackDate) {
        this.exbackDate = exbackDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setIsAprove(int isAprove) {
        this.isAprove = isAprove;
    }
}
