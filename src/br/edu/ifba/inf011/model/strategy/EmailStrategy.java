package br.edu.ifba.inf011.model.strategy;

import java.util.List;
import java.util.function.Predicate;

import br.edu.ifba.inf011.model.evento.Evento;

public 	class EmailStrategy implements FormaEnvioStrategy {
	
	private List<Predicate<Evento>> condicoes;
	private FormaEnvioStrategy proxima;
	private boolean adicionarAoGoogleCalendar;
	
	public EmailStrategy(List<Predicate<Evento>> condicoes, FormaEnvioStrategy proxima, boolean adicionarAoGoogleCalendar) {
        this.condicoes = condicoes;
        this.proxima = proxima;
        this.adicionarAoGoogleCalendar = adicionarAoGoogleCalendar;
    }
	    @Override
	    public String formatarMensagem(String m) {
	        return "Mensagem formatada para e-mail: " + m;
	    }

	    @Override
	    public void enviarMensagem(Evento e) {
	    	if (deveAplicar(e)) {
	    		String mensagem = this.formatarMensagem(e.getDescricao());
	        System.out.println("Enviando e-mail: " + mensagem);
	    }
		if (proxima != null) {
			proxima.enviarMensagem(e);
		}
	}
	    
	    @Override
	    public boolean deveAplicar(Evento evento) {
	        return condicoes.stream().anyMatch(condicao -> condicao.test(evento));
	    }
		@Override
		public boolean deveAdicionarAoGoogleCalendar() { 
			return adicionarAoGoogleCalendar;
		}
	}


