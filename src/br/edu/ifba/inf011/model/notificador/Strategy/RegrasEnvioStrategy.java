package br.edu.ifba.inf011.model.notificador.Strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public interface RegrasEnvioStrategy {
    public boolean verificar(Evento evento);
}
