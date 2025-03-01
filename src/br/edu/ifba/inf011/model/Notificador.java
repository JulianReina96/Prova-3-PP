package br.edu.ifba.inf011.model;

import java.util.List;



import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;

public	class Notificador {
	    private FormaEnvioStrategy estrategia;

	    public Notificador(FormaEnvioStrategy estrategia) {
	        this.estrategia = estrategia;
	    }

	    public void notificar(Evento evento) {
	        estrategia.enviarMensagem(evento);
	    }
	}

