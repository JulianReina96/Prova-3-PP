package br.edu.ifba.inf011.model.ChainResponsibility;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import br.edu.ifba.inf011.model.dto.NotificacaoInfo;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;

public class Prioridade1a5Handler implements EventoHandler {
    private EventoHandler proximoHandler;

    @Override
    public void setProximoHandler(EventoHandler handler) {
        this.proximoHandler = handler;
    }

    @Override
    public NotificacaoInfo lidarComEvento(Evento e) {
        if (e.getPrioridade() >= 1 && e.getPrioridade() < 5 &&
                e.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS), LocalDateTime.now())) {
            return new NotificacaoInfo(Arrays.asList(new EmailStrategy()), true);
        } else if (proximoHandler != null) {
            return proximoHandler.lidarComEvento(e);
        }
        return null;
    }
}