package info6205.slack.users;

public abstract class User {
    String id;
    String realName;
    boolean isAdmin;
    boolean isOwner;

    public String getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isOwner() {
        return isOwner;
    }
}
