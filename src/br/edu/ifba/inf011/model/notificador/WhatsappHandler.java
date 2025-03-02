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

    public WhatsappHandler(Notificador proximo) {
        super(List.of(new Prior10AndNowStrategy()), proximo);
    }

    public WhatsappHandler(List<RegrasEnvioStrategy> regra) {
        super(regra, null);
    }

    public WhatsappHandler() {
        super(List.of(new Prior10AndNowStrategy()), null);
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
