package Model;

public class Användare {
    //fields
    private Long Id;
    private String Namn;
    private String LoginiId;
    private String password;
    private String Role;
    private Long id_djur;

    //constructor
    public Användare(Long id, String namn, String loginiId, String password, String role, Long id_djur) {
        Id = id;
        Namn = namn;
        LoginiId = loginiId;
        this.password = password;
        Role = role;
        this.id_djur = id_djur;
    }

    public Användare() {
    }

    // getters and setters
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

    public Long getId_djur() {
        return id_djur;
    }

    public void setId_djur(Long id_djur) {
        this.id_djur = id_djur;
    }
}

