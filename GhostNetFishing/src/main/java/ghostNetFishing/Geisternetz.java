package ghostNetFishing;

public class Geisternetz {
    private int id;
    private GPS standort;
    private float größe;
    private Geisternetzstatus status;

    public Geisternetz(int id, GPS standort, float größe, Geisternetzstatus status) {
        this.id = id;
        this.standort = standort;
        this.größe = größe;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
