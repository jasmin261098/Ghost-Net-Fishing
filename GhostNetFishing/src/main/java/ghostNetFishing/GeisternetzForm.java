package ghostNetFishing;

import jakarta.enterprise.context.RequestScoped; 
import jakarta.inject.Named;                     
import java.io.Serializable;

@Named
@RequestScoped // or @ViewScoped depending on your needs
public class GeisternetzForm implements Serializable {
    private Geisternetz geisternetz = new Geisternetz();  // Initialize the Geisternetz object

    // Getter and Setter for geisternetz
    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }
}
