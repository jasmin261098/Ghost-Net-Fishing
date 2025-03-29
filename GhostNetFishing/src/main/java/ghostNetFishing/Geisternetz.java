package ghostNetFishing;

import jakarta.persistence.*;

@Entity
public class Geisternetz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische ID-Vergabe
    private int id;
    
    @Column(nullable = true) // Bergende Person kann leer sein
    private int nr;

    @Embedded // Falls GPS eine eigene @Embeddable Klasse ist
    private GPS standort;

    @Column(nullable = false) // Größe darf nicht null sein
    private float größe;

    @Enumerated(EnumType.STRING) // Speichert das Enum als String in der DB
    @Column(nullable = false)
    private Geisternetzstatus status;

    @Column(nullable = true) // Bergende Person kann leer sein
    private String bergendePerson = "";

    // Standard-Konstruktor für Hibernate
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
