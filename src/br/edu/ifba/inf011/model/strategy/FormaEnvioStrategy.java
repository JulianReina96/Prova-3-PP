package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public interface FormaEnvioStrategy {
	
	String formatarMensagem(Evento e);
    void enviarMensagem(String mensagem);

}
