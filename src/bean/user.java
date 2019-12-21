package bean;

public class user {

    private String userId;
    private String userPasswrod;
    private String limit;

    public String getLimit() {
        return limit;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPasswrod() {
        return userPasswrod;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setUserPasswrod(String userPasswrod) {
        this.userPasswrod = userPasswrod;
    }

}
