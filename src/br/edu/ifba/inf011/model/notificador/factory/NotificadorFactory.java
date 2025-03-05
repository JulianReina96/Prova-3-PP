package br.edu.ifba.inf011.model.notificador.factory;

import java.util.List;
import br.edu.ifba.inf011.model.notificador.*;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;




public class NotificadorFactory {
	
	public static Notificador notificarEmail(List<RegrasEnvioStrategy> regras, Notificador proximo) {
        return new EmailHandler(regras, proximo);
    }

    public static Notificador notificarWhatsapp(List<RegrasEnvioStrategy> regras, Notificador proximo) {
        return new WhatsappHandler(regras, proximo);
    }

    public static Notificador notificarGoogleCalendar(List<RegrasEnvioStrategy> regras, Notificador proximo) {
        return new GoogleCalendarHandler(regras, proximo);
    }

}
