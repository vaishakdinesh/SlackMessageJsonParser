package info6205.slack.users;

public class TA extends User {

    public TA(String id, String real_name, boolean is_owner, boolean is_admin){
        this.id = id;
        this.realName = real_name;
        this.isOwner = is_owner;
        this.isAdmin = is_admin;
    }

}
