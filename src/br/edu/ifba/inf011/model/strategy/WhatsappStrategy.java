package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public class WhatsappStrategy implements FormaEnvioStrategy {
    @Override
    public String formatarMensagem(Evento e) {
        return "Mensagem formatada para WhatsApp: " + e.getDescricao();
    }

    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("Enviando WhatsApp: " + mensagem);
    }
}


