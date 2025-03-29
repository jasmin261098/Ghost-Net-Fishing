package ghostNetFishing;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("geisternetzController")
@ViewScoped
public class GeisternetzController implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String latitude;
	private String longitude;
    private int nr;
    private float größe;

	
	@Inject
	private Geisternetzliste geisternetzliste;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getNr() {
        return nr;
    }
    public void setNr(int nr) {
        this.nr = nr;
    }
    public float getGröße() {
        return größe;
    }
    public void setGröße(float größe) {
        this.größe = größe;
    }

	
	public String meldeGeisternetz() {
		float lat = Float.parseFloat(latitude);
		float lon = Float.parseFloat(longitude);
		Geisternetz geisternetz = new Geisternetz(this.nr, new GPS(lat, lon), this.größe, Geisternetzstatus.GEMELDET);
		
        geisternetzliste.addGeisternetz(geisternetz);
        
        return "index?faces-redirect=true";
	}
	


}
