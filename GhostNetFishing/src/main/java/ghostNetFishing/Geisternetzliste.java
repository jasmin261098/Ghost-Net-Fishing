package ghostNetFishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("geisternetzListe")
@ApplicationScoped
public class Geisternetzliste implements Serializable {
    private List<Geisternetz> liste = new ArrayList<>();

    public Geisternetzliste() {
        // Initialize with some default geisternetz objects (optional for testing)
        GPS gps1 = new GPS(52.379189f, 4.900093f);
        Geisternetz net1 = new Geisternetz(1, gps1, 15.5f, Geisternetzstatus.GEMELDET);
        
        GPS gps2 = new GPS(53.379189f, 5.900093f);
        Geisternetz net2 = new Geisternetz(2, gps2, 20.0f, Geisternetzstatus.BERGUNG_BEVORSTEHEND);

        liste.add(net1);
        liste.add(net2);
    }

    // Method to add a new Geisternetz to the list
    public String meldeGeisternetz(Geisternetz geisternetz) {
        liste.add(geisternetz); // Adds the new Geisternetz to the list
        return "index?faces-redirect=true"; // Redirects to index.xhtml to display the updated list
    }

    // Getter for the list of geisternetz
    public List<Geisternetz> getListe() {
        return liste;
    }
}
