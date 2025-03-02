package br.edu.ifba.inf011.model.notificador;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.notificador.Strategy.Prior5AndNowStrategy;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;
import java.util.List;

/*
 *  CHAVE DO EMAIL DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
 */
public class EmailHandler extends AbstractNotifyHandler {

    public EmailHandler(List<RegrasEnvioStrategy> regra, Notificador proximo) {
        super(regra, proximo);
    }

    public EmailHandler(Notificador proximo) {
        super(List.of(new Prior5AndNowStrategy()), proximo);
    }

    public EmailHandler(List<RegrasEnvioStrategy> regra) {
        super(regra, null);
    }

    public EmailHandler() {
        super(List.of(new Prior5AndNowStrategy()), null);
    }

    @Override
    public void doNotificar(String mensagem) {
        System.out.println("ENVIANDO " + mensagem + " PARA EMAIL");
    }

    @Override
    public String formatar(Evento evento) {
        System.out.println("FORMATANDO MENSAGEM PARA EMAIL");
        return super.formatar(evento);
    }

}
