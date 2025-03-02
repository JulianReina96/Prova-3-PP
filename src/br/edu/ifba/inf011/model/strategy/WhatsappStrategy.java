package br.edu.ifba.inf011.model.strategy;

import java.util.List;
import java.util.function.Predicate;

import br.edu.ifba.inf011.model.evento.Evento;

public class WhatsappStrategy implements FormaEnvioStrategy {
	
	private List<Predicate<Evento>> condicoes;
	private FormaEnvioStrategy proxima;
	private boolean adicionarAoGoogleCalendar;
	
	public WhatsappStrategy(List<Predicate<Evento>> condicoes, FormaEnvioStrategy proxima, boolean adicionarAoGoogleCalendar) {
        this.condicoes = condicoes;
        this.proxima = proxima;
        this.adicionarAoGoogleCalendar = adicionarAoGoogleCalendar;
    }
    @Override
    public String formatarMensagem(String mensagem) {
        return "Mensagem formatada para WhatsApp: " + mensagem;
    }
     
    @Override
	public boolean deveAdicionarAoGoogleCalendar() {	
		return adicionarAoGoogleCalendar;
	}
    @Override
    public void enviarMensagem(Evento evento) {
    	if (deveAplicar(evento)) {
    		String mensagem = this.formatarMensagem(evento.getDescricao());  		
    		
        System.out.println("Enviando WhatsApp: " + mensagem);
        }
    	if (proxima != null) {
            proxima.enviarMensagem(evento);
        }
    }
    
    @Override
    public boolean deveAplicar(Evento evento) {
        return condicoes.stream().anyMatch(condicao -> condicao.test(evento));
    }
	
}


