package br.edu.ifba.inf011.model.notificador;

import java.time.LocalDate;

import br.edu.ifba.inf011.model.evento.Evento;

/*
 *  CHAVE DO EMAIL DA CADEIA DE REPONSABILIDADE DA NOTIFICAÇÃO
*/
public class EmailHandler extends AbstractNotifyHandler{

	public EmailHandler(Notificador proximo) {
		super(proximo);
	}
	
	public EmailHandler() {
		super();
	}
	
	@Override
	public boolean devoNotificar(Evento evento) {
		if (evento.iniciaEm(LocalDate.now()) && (evento.getPrioridade() >= 5))
			return true;

		return false;
	}

	@Override
	public void doNotificar(Evento evento) {
		System.out.println("ENVIANDO PARA EMAIL");
	}
	
	@Override
	public void formatar(Evento evento) {
		System.out.println("FORMATANDO MENSAGEM PARA EMAIL");
	}

}
