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
