package bean;

public class dormitoryManager {

    private String managerNo;
    private String buildingNo;
    private String managerName;
    private String managerTel;

    public String getBuildingNo() {
        return buildingNo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel;
    }
}
