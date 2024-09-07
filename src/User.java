public abstract class User extends Identifiable implements Printable {

    private final String role;

    protected User(String role) {
        this.role = role;
    }

    void printRole() {
        System.out.println("Role: " + role);
    }
}
