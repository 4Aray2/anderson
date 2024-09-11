package user;

public abstract class User extends Identifiable implements Printable {

    private final String role;

    protected User(String role) {
        this.role = role;
    }

    protected void printRole() {
        System.out.println("Role: " + role);
    }
}
