package br.edu.ifba.inf011.model.notificador.Strategy;

import br.edu.ifba.inf011.model.evento.Evento;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Prior1AndTodayTo2DaysAgo implements RegrasEnvioStrategy {

    @Override
    public boolean verificar(Evento evento) {
        return (evento.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS), LocalDateTime.now()))
                && (evento.getPrioridade() != null && evento.getPrioridade() >= 1 && evento.getPrioridade() <= 5);

    }
}
