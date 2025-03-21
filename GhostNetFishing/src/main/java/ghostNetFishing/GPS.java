package ghostNetFishing;

public class GPS {
    private float längengrad;
    private float breitengrad;

    public GPS(float längengrad, float breitengrad) {
        this.längengrad = längengrad;
        this.breitengrad = breitengrad;
    }

    public float getLängengrad() {
        return längengrad;
    }

    public void setLängengrad(float längengrad) {
        this.längengrad = längengrad;
    }

    public float getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(float breitengrad) {
        this.breitengrad = breitengrad;
    }
    
    @Override
    public String toString() {
        return "Längengrad: " + this.längengrad + ", Breitengrad: " + this.breitengrad;
    }
}
