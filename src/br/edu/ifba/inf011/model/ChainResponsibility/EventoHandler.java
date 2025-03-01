package br.edu.ifba.inf011.model.ChainResponsibility;

import br.edu.ifba.inf011.model.dto.NotificacaoInfo;
import br.edu.ifba.inf011.model.evento.Evento;

public interface EventoHandler {
    void setProximoHandler(EventoHandler handler);
    NotificacaoInfo lidarComEvento(Evento e);
}