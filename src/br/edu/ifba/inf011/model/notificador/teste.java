package br.edu.ifba.inf011.model.notificador;

import java.time.LocalDateTime;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.evento.TipoEvento;
import br.edu.ifba.inf011.model.evento.builder.BuilderPartida;

public class teste {
	public static void main(String[] args) {
		Evento e = new BuilderPartida().setPrioridade(10).setInicio(LocalDateTime.now()).build(TipoEvento.LEMBRETE, "Evento Foda");
		
		Notificador notify = new GoogleCalendarHandler(new WhatsappHandler(new EmailHandler()));
		
		notify.Notificar(e);
	}
}
