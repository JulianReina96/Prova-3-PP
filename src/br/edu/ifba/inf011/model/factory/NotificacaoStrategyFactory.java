package br.edu.ifba.inf011.model.factory;


import java.util.List;
import java.util.function.Predicate;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;
import br.edu.ifba.inf011.model.strategy.WhatsappStrategy;
import br.edu.ifba.inf011.model.evento.Evento;

public class NotificacaoStrategyFactory {
    public static FormaEnvioStrategy criarEstrategiaWhatsApp(List<Predicate<Evento>> condicoes, FormaEnvioStrategy proxima, boolean adicionarAoGoogleCalendar) {
        return new WhatsappStrategy(condicoes, proxima, adicionarAoGoogleCalendar);
    }

    public static FormaEnvioStrategy criarEstrategiaEmail(List<Predicate<Evento>> condicoes, FormaEnvioStrategy proxima, boolean adicionarAoGoogleCalendar) {
        return new EmailStrategy(condicoes, proxima, adicionarAoGoogleCalendar);
    }
}
