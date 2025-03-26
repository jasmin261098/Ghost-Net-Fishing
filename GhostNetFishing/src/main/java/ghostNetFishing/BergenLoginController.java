package ghostNetFishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("bergenLoginController")
@ViewScoped
public class BergenLoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private BergendePerson bergendePerson;
	
	@Inject AktuellerBenutzer aktuellerBenutzer;
	

	List<BergendePerson> benutzerListe;

	public BergenLoginController() {
		this.benutzerListe = new ArrayList<BergendePerson>();
		this.benutzerListe.add(new BergendePerson("OceanWarrior", "2016"));
		this.benutzerListe.add(new BergendePerson("PirateWarrior", "2016"));
		this.bergendePerson = new BergendePerson();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BergendePerson getBergendePerson() {
		return bergendePerson;
	}

	public void setBergendePerson(BergendePerson bergendePerson) {
		this.bergendePerson = bergendePerson;
	}
	
	public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
		UIInput temp = (UIInput)ev.getComponent();
		this.name = (String)temp.getValue();
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		for(BergendePerson b:benutzerListe) {
			BergendePerson temp = new BergendePerson(this.name, (String)value);
			if(b.equals(temp))
				this.bergendePerson = temp;
				aktuellerBenutzer.setName(temp.name);
				return;
		}
		throw new ValidatorException(new FacesMessage("Login fehlgeschlagen!"));
	}

	public String login() {
		return "bergen";
	}
}

