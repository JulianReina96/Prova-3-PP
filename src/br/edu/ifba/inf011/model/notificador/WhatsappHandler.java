package br.edu.ifba.inf011.model.notificador;

import java.time.LocalDate;

import br.edu.ifba.inf011.model.evento.Evento;

/*
 *  CHAVE DO WHATSAPP DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
*/
public class WhatsappHandler extends AbstractNotifyHandler{

	@Override
	public boolean devoNotificar(Evento evento) {
		if (evento.iniciaEm(LocalDate.now()) && (evento.getPrioridade() == 10))
			return true;

		return false;
	}

	@Override
	public void doNotificar(Evento evento) {
		System.out.println("ENVIANDO PARA WHATSAPP");
	}
	
	@Override
	public void formatar(Evento evento) {
		System.out.println("FORMATANDO MENSAGEM PARA WHATSAPP");
	}

}
