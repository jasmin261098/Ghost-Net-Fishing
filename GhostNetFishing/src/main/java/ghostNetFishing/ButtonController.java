package ghostNetFishing;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ButtonController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean disable;
	
	
	public ButtonController() {
		this.disable = false;
	}

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
