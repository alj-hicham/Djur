package Model;

public class Val {
    //fields
    private Long id;
    private Användare användaren;
    private Djur djur;
    private Long ID_anv;
    private Long id_djur;

    //constructor
    public Val() {

    }

    public Val(Long id, Användare användaren, Djur djur) {
        this.id = id;
        this.användaren = användaren;
        this.djur = djur;
    }

    public Val(Long id, Long ID_anv, Long id_djur) {
        this.ID_anv = ID_anv;
        this.id = id;
        this.id_djur = id_djur;

    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Användare getAnvändaren() {
        return användaren;
    }

    public void setAnvändaren(Användare användaren) {
        this.användaren = användaren;
    }

    public Djur getDjur() {
        return djur;
    }

    public void setDjur(Djur djur) {
        this.djur = djur;
    }

    public Long getID_anv() {
        return ID_anv;
    }

    public void setID_anv(Long ID_anv) {
        this.ID_anv = ID_anv;
    }

    public Long getId_djur() {
        return id_djur;
    }

    public void setId_djur(Long id_djur) {
        this.id_djur = id_djur;
    }
}
