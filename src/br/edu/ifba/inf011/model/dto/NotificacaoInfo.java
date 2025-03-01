package br.edu.ifba.inf011.model.dto;

import java.util.List;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;

public class NotificacaoInfo {
    private List<FormaEnvioStrategy> estrategias;
    private boolean googleCalendar;

    public NotificacaoInfo(List<FormaEnvioStrategy> estrategias, boolean googleCalendar) {
        this.estrategias = estrategias;
        this.googleCalendar = googleCalendar;
    }	



	public List<FormaEnvioStrategy> getEstrategias() {
        return estrategias;
    }

	public boolean getGoogleCalendar() {
        return googleCalendar;
    }
   
}
