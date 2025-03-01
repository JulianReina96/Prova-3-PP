package br.edu.ifba.inf011.model;

import java.util.List;

import br.edu.ifba.inf011.model.ChainResponsibility.EventoHandler;
import br.edu.ifba.inf011.model.ChainResponsibility.Prioridade10Handler;
import br.edu.ifba.inf011.model.ChainResponsibility.Prioridade1a5Handler;
import br.edu.ifba.inf011.model.ChainResponsibility.Prioridade5Handler;
import br.edu.ifba.inf011.model.dto.NotificacaoInfo;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;

public  class Notificador {
    private EventoHandler primeiroHandler;

    public Notificador() {
        this.primeiroHandler = criarCadeiaDeHandlers();
    }


    private EventoHandler criarCadeiaDeHandlers() {
        EventoHandler prioridade10Handler = new Prioridade10Handler();
        EventoHandler prioridade5Handler = new Prioridade5Handler();
        EventoHandler prioridade1a5Handler = new Prioridade1a5Handler();

        prioridade10Handler.setProximoHandler(prioridade5Handler);
        prioridade5Handler.setProximoHandler(prioridade1a5Handler);

        return prioridade10Handler;
    }

    public void notificar(Evento e) {
      NotificacaoInfo estrategias = primeiroHandler.lidarComEvento(e);
        if (estrategias != null) {
            for (FormaEnvioStrategy estrategia : estrategias.getEstrategias()) {
                estrategia.enviarMensagem(estrategia.formatarMensagem(e));
            }
            if (estrategias.getGoogleCalendar()) {
            	System.out.println("Evento adicionado ao Google Calendar");
            }
        }
    }
}
