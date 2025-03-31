package ghostNetFishing;

import jakarta.persistence.*;

@Entity
public class Geisternetz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    @Column(nullable = true) 
    private int nr;

    @Embedded 
    private GPS standort;

    @Column(nullable = false)
    private float größe;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    private Geisternetzstatus status;

    @Column(nullable = true) 
    private String bergendePerson = "";

    public Geisternetz() {}

    public Geisternetz(int nr, GPS standort, float größe, Geisternetzstatus status) {
        this.nr = nr;
        this.standort = standort;
        this.größe = größe;
        this.status = status;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public GPS getStandort() {
        return standort;
    }

    public void setStandort(GPS standort) {
        this.standort = standort;
    }

    public float getGröße() {
        return größe;
    }

    public void setGröße(float größe) {
        this.größe = größe;
    }

    public Geisternetzstatus getStatus() {
        return status;
    }

    public void setStatus(Geisternetzstatus status) {
        this.status = status;
    }

    public String getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(String bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
}
