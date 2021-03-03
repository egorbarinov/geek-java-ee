package ru.geekbrains.persist;

public enum Role {
    ROLE_USER("USER"),
    ROLE_MANAGER("MANAGER"),
    ROLE_ADMIN("ADMIN");


    private final String role;

    private Role(final String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
