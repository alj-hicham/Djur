package Model;

public class Val {

    private Long id;
    private Användaren användaren;
    private Djur djur;


    public Val(Long id, Användaren användaren, Djur djur) {
        this.id = id;
        this.användaren = användaren;
        this.djur = djur;
    }

    public Val() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Användaren getAnvändaren() {
        return användaren;
    }

    public void setAnvändaren(Användaren användaren) {
        this.användaren = användaren;
    }

    public Djur getDjur() {
        return djur;
    }

    public void setDjur(Djur djur) {
        this.djur = djur;
    }
}
