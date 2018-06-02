package Model;

//detta är model class för Djur
public class Djur {

    private Long Id;
    private String Namn;
    private int fluffighet;
    private int mysighet;
    private int mosighet;
    private int Illaluktande;
    private int smarthet;
    private int Starlek;

    // construstor


    public Djur(Long id, String namn, int fluffighet, int mysighet, int mosighet, int illaluktande, int smarthet, int starlek) {
        Id = id;
        Namn = namn;
        this.fluffighet = fluffighet;
        this.mysighet = mysighet;
        this.mosighet = mosighet;
        Illaluktande = illaluktande;
        this.smarthet = smarthet;
        Starlek = starlek;
    }

    public Djur() {
    }

    //getters and setters
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

    public int getFluffighet() {
        return fluffighet;
    }

    public void setFluffighet(int fluffighet) {
        this.fluffighet = fluffighet;
    }

    public int getMysighet() {
        return mysighet;
    }

    public void setMysighet(int mysighet) {
        this.mysighet = mysighet;
    }

    public int getMosighet() {
        return mosighet;
    }

    public void setMosighet(int mosighet) {
        this.mosighet = mosighet;
    }

    public int getIllaluktande() {
        return Illaluktande;
    }

    public void setIllaluktande(int illaluktande) {
        Illaluktande = illaluktande;
    }

    public int getSmarthet() {
        return smarthet;
    }

    public void setSmarthet(int smarthet) {
        this.smarthet = smarthet;
    }

    public int getStarlek() {
        return Starlek;
    }

    public void setStarlek(int starlek) {
        Starlek = starlek;
    }
}
