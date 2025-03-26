package ghostNetFishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("geisternetzListe")
@ApplicationScoped
public class Geisternetzliste implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private List<Geisternetz> liste = new ArrayList<>();

    public Geisternetzliste() {
        GPS gps1 = new GPS(52.379189f, 4.900093f);
        Geisternetz net1 = new Geisternetz(1, gps1, 15.5f, Geisternetzstatus.GEMELDET);
        
        GPS gps2 = new GPS(53.379189f, 5.900093f);
        Geisternetz net2 = new Geisternetz(2, gps2, 20.0f, Geisternetzstatus.BERGUNG_BEVORSTEHEND);

        liste.add(net1);
        liste.add(net2);
    }
    
    public void addGeisternetz(Geisternetz geisternetz) {
    	liste.add(geisternetz);
    }
    
	public void bergungEintragen(int id, String aktuellerBenutzer) {
        for (Geisternetz netz : this.getListe()) {
            if (netz.getId() == id) {
                netz.setStatus(Geisternetzstatus.BERGUNG_BEVORSTEHEND);
                netz.setBergendePerson(aktuellerBenutzer);
                break; 
            }
        }
	}

    public List<Geisternetz> getListe() {
        return liste;
    }
}
