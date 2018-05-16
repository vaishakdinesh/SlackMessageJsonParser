package info6205.slack.users;

public class Professor extends User {

    public Professor(String id, String real_name, boolean is_owner, boolean is_admin){
        this.id = id;
        this.realName = real_name;
        this.isOwner = is_owner;
        this.isAdmin = is_admin;
    }
}
