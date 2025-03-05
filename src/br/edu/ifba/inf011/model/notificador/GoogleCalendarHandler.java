package br.edu.ifba.inf011.model.notificador;

import br.edu.ifba.inf011.model.notificador.Strategy.Prior10AndNowStrategy;
import br.edu.ifba.inf011.model.notificador.Strategy.Prior1AndTodayTo2DaysAgo;
import br.edu.ifba.inf011.model.notificador.Strategy.Prior5AndNowStrategy;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;
import java.util.List;

/*
 *  CHAVE DO GOOGLE CALENDAR DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
 */
public class GoogleCalendarHandler extends AbstractNotifyHandler {

    public GoogleCalendarHandler(List<RegrasEnvioStrategy> regra, Notificador proximo) {
        super(regra, proximo);
    }

    @Override
    public void doNotificar(String e) {
        System.out.println("SALVANDO " + e + " NO GOOGLE CALENDAR");
    }

}
