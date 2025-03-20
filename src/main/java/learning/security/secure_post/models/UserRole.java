package learning.security.secure_post.models;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    UserRole(String role) {
        this.role = role;
    }

    private String role;

    public String get() {
        return role;
    }
}
