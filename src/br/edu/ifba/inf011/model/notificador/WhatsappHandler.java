package br.edu.ifba.inf011.model.notificador;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.notificador.Strategy.Prior10AndNowStrategy;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;
import java.util.List;

/*
 *  CHAVE DO WHATSAPP DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
 */
public class WhatsappHandler extends AbstractNotifyHandler {

    public WhatsappHandler(List<RegrasEnvioStrategy> regra, Notificador proximo) {
        super(regra, proximo);
    }

    @Override
    public void doNotificar(String mensagem) {
        System.out.println("ENVIANDO " + mensagem + " PARA WHATSAPP");
    }

    @Override
    public String formatar(Evento evento) {
        System.out.println("FORMATANDO MENSAGEM PARA WHATSAPP");
        return super.formatar(evento);
    }

}
