package ghostNetFishing;

public class BergendePerson {
	String name;
	String passwort;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	public BergendePerson(String name, String passwort) {
		this.name = name;
		this.passwort = passwort;
	}
	public BergendePerson() {
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BergendePerson) {
			BergendePerson b = (BergendePerson)obj;
			if(b.getName().equals(this.name) && b.getPasswort().equals(this.passwort)) {
				return true;
			}
		}
		return false;
	}
}
