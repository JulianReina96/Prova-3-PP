package br.edu.ifba.inf011.model.notificador.Strategy;

import br.edu.ifba.inf011.model.evento.Evento;
import java.time.LocalDate;

public class Prior10AndNowStrategy implements RegrasEnvioStrategy {

    @Override
    public boolean verificar(Evento evento) {
        return evento.getPrioridade() != null && evento.getPrioridade() == 10 && evento.iniciaEm(LocalDate.now());
    }
}
