package Model;

public class Användaren {
    private Long Id;
    private String Namn;
    private String LoginiId;
    private String password;
    private String Role;


    public Användaren(Long id, String namn, String loginiId, String password, String role) {
        Id = id;
        Namn = namn;
        LoginiId = loginiId;
        this.password = password;
        Role = role;
    }

    public Användaren() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNamn() {
        return Namn;
    }

    public void setNamn(String namn) {
        Namn = namn;
    }

    public String getLoginiId() {
        return LoginiId;
    }

    public void setLoginiId(String loginiId) {
        LoginiId = loginiId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}

