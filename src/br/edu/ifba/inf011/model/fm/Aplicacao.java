package br.edu.ifba.inf011.model.fm;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import br.edu.ifba.inf011.academico.DatabaseAcademico;
import br.edu.ifba.inf011.model.Calendario;
import br.edu.ifba.inf011.model.Equipe;
import br.edu.ifba.inf011.model.Geolocalizacao;
import br.edu.ifba.inf011.model.Notificador;
import br.edu.ifba.inf011.model.adapter.AulaObjectAdapter;
import br.edu.ifba.inf011.model.decorator.OnlineCalendarioDecorator;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.evento.Lembrete;
import br.edu.ifba.inf011.model.evento.Partida;
import br.edu.ifba.inf011.model.evento.TipoEvento;
import br.edu.ifba.inf011.model.evento.builder.BuilderPartida;
import br.edu.ifba.inf011.model.strategy.FormaEnvioStrategy;
import br.edu.ifba.inf011.model.factory.NotificacaoStrategyFactory;
import br.edu.ifba.inf011.model.observer.GoogleCalendarObserver;

public class Aplicacao extends AplicacaoCalendario{

	
	@Override
	protected Calendario createCalendario(){
		try {
			return 
					new OnlineCalendarioDecorator(
							new OnlineCalendarioDecorator(
									new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024), 
							"http://feriados.com"),
					"http://detroitlions.com");
		}catch(Exception ex) {
			return new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024);
		}	
	}

	
	public void run() throws Exception {
		
		DatabaseAcademico database = new DatabaseAcademico();
		
		
		
		this.adicionarEvento(((BuilderPartida) 
				  			   Partida.builder().init()
				  					  .setInicio(LocalDateTime.of(1970, 6, 21, 12, 0))
				  					  .setLocalizacao(new Geolocalizacao("Estádio Azteca - Cidade do México")))
					  				  .setPlacar(4, 1)
					  				  .build("Final da Copa do Mundo de 1970", new Equipe("Brasil"), new Equipe("Italia")));
		
		this.adicionarEvento(Lembrete.builder().init()
							.setInicio(LocalDateTime.of(2025, 02, 03, 19, 30))
							.build(TipoEvento.LEMBRETE, "Avaliação II de Padrões de Projeto"));
		
		this.adicionarEvento(Lembrete.builder().init()
				.setInicio(LocalDateTime.now())
				.build(TipoEvento.LEMBRETE, "Reunião Semanal"));
		
		this.adicionarEvento(Lembrete.builder().init()
				.setInicio(LocalDateTime.of(2025, 02, 28, 19, 30))
				.build(TipoEvento.LEMBRETE, "Avaliação III de Padrões de Projeto"));
		
		this.adicionarEvento(new AulaObjectAdapter(database.getAula("INF011")));
		
		
		Collection<Evento> hoje = this.today();
		
		for(Evento e : hoje)
			System.out.println(e.getDescricao());
		
		//podemos ter classes handlers inves de codiçoes estaticas
		List<Predicate<Evento>> condicoesWhatsApp = new ArrayList<>();
        condicoesWhatsApp.add(evento -> evento.getPrioridade() == 10 && evento.iniciaEm(LocalDate.now()));
        condicoesWhatsApp.add(evento -> evento.getPrioridade() == 5 && evento.iniciaEm(LocalDate.now()));

        List<Predicate<Evento>> condicoesEmail = new ArrayList<>();
        condicoesEmail.add(evento -> evento.getPrioridade() >= 5 && evento.iniciaEm(LocalDate.now()));
        condicoesEmail.add(evento -> evento.getPrioridade() >= 1 && evento.getPrioridade() < 5 &&
                                              evento.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS), LocalDateTime.now()));

        // Criação das estratégias usando a fábrica
        FormaEnvioStrategy estrategiaWhatsApp = NotificacaoStrategyFactory.criarEstrategiaWhatsApp(condicoesWhatsApp, null, true);
        FormaEnvioStrategy estrategiaEmail = NotificacaoStrategyFactory.criarEstrategiaEmail(condicoesEmail, estrategiaWhatsApp, true);


        
		for (Evento e : hoje) {
			
	            GoogleCalendarObserver googleCalendarObserver = new GoogleCalendarObserver(estrategiaEmail);
	            Notificador notificador = new Notificador(googleCalendarObserver);
	            notificador.notificar(e);
	        
			
		}
		
	}
	



	public static void main(String[] args) throws Exception {
		(new Aplicacao()).run();
	}

}
