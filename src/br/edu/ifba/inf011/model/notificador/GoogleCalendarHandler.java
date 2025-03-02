package br.edu.ifba.inf011.model.notificador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import br.edu.ifba.inf011.model.evento.Evento;

/*
 *  CHAVE DO GOOGLE CALENDAR DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
*/
public class GoogleCalendarHandler extends AbstractNotifyHandler {
	
	public GoogleCalendarHandler(Notificador proximo) {
		super(proximo);
	}
	
	public GoogleCalendarHandler() {
		super();
	}
	
	@Override
	public boolean devoNotificar(Evento evento) {
		if (evento.iniciaEm(LocalDate.now()) && (evento.getPrioridade() >= 1 && evento.getPrioridade() <= 10))
			return true;

		if (evento.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS), LocalDateTime.now())
				&& (evento.getPrioridade() >= 1 && evento.getPrioridade() <= 5))
			return true;

		return false;
	}

	@Override
	public void doNotificar(Evento e) {
		System.out.println("SALVANDO NO GOOGLE CALENDAR");
	}

}
