package br.edu.ifba.inf011.model.observer;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;

public class GoogleCalendarObserver implements FormaEnvioStrategy {
    private FormaEnvioStrategy proxima;
    private boolean adicionadoAoCalendar = false;

    public GoogleCalendarObserver(FormaEnvioStrategy proxima) {
        this.proxima = proxima;
    }

    @Override
    public boolean deveAplicar(Evento evento) {
        return proxima != null && proxima.deveAplicar(evento);
    }

    @Override
    public void enviarMensagem(Evento evento) {
        if (proxima != null) {
            proxima.enviarMensagem(evento);
        }
        if (!adicionadoAoCalendar && proxima != null && proxima.deveAdicionarAoGoogleCalendar()) {
            adicionarAoGoogleCalendar(evento.getDescricao());
            adicionadoAoCalendar = true;
        }
    }

    @Override
    public String formatarMensagem(String mensagem) {
        return proxima != null ? proxima.formatarMensagem(mensagem) : "";
    }

    

    private void adicionarAoGoogleCalendar(String mensagem) {
    	System.out.println("Adicionando evento ao Google Calendar: " + mensagem);
    }

	@Override
	public boolean deveAdicionarAoGoogleCalendar() {
		return false;
	}
}
