package Back_End.model;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private int status;
    private String createdAt;

    public User() {}

    // constructor
    public User(int userId, String firstName, String lastName, String email,
                String username, String password, String role, int status, String createdAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters
    public int getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public int getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }

    // setters
    public void setUserId(int userId) { this.userId = userId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setStatus(int status) { this.status = status; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}