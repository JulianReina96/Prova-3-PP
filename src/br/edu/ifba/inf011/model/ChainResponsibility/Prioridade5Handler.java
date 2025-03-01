package br.edu.ifba.inf011.model.ChainResponsibility;

import java.time.LocalDate;
import java.util.Arrays;

import br.edu.ifba.inf011.model.dto.NotificacaoInfo;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;

public class Prioridade5Handler implements EventoHandler {
    private EventoHandler proximoHandler;

    @Override
    public void setProximoHandler(EventoHandler handler) {
        this.proximoHandler = handler;
    }

    @Override
    public NotificacaoInfo lidarComEvento(Evento e) {
        if (e.getPrioridade() >= 5 && e.iniciaEm(LocalDate.now())) {
            return new NotificacaoInfo(Arrays.asList(new EmailStrategy()), true);
        } else if (proximoHandler != null) {
            return proximoHandler.lidarComEvento(e);
        }
        return null;
    }


}