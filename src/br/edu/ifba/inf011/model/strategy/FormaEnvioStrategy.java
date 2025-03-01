package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public interface FormaEnvioStrategy {
	
	String formatarMensagem(String m);
    void enviarMensagem(Evento e);
    boolean deveAplicar(Evento e);
    boolean deveAdicionarAoGoogleCalendar();

}
