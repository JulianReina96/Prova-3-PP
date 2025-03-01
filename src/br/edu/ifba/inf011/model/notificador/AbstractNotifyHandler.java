package br.edu.ifba.inf011.model.notificador;

import br.edu.ifba.inf011.model.evento.Evento;

/*
 *  Template Method
*/
public abstract class AbstractNotifyHandler implements Notificador {
	private Notificador proximo;
	
	public AbstractNotifyHandler(Notificador proximo) {
		this.proximo = proximo;
	}
	
	public AbstractNotifyHandler() {
		this(null);
	}
	
	@Override
	public void Notificar(Evento evento) {
		if(this.devoNotificar(evento)) {
			this.formatar(evento);
			this.doNotificar(evento);
		}
		
		if(proximo != null)
			proximo.Notificar(evento);
		
	}
	
	public abstract boolean devoNotificar(Evento evento);
	public abstract void doNotificar(Evento evento);
	
	public void formatar(Evento evento) {}

}
