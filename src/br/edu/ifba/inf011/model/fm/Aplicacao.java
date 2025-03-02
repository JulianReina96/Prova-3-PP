package br.edu.ifba.inf011.model.fm;

import br.edu.ifba.inf011.academico.DatabaseAcademico;
import br.edu.ifba.inf011.model.Calendario;
import br.edu.ifba.inf011.model.Equipe;
import br.edu.ifba.inf011.model.Geolocalizacao;
import br.edu.ifba.inf011.model.adapter.AulaObjectAdapter;
import br.edu.ifba.inf011.model.decorator.OnlineCalendarioDecorator;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.evento.Lembrete;
import br.edu.ifba.inf011.model.evento.Partida;
import br.edu.ifba.inf011.model.evento.TipoEvento;
import br.edu.ifba.inf011.model.evento.builder.BuilderPartida;
import br.edu.ifba.inf011.model.notificador.EmailHandler;
import br.edu.ifba.inf011.model.notificador.GoogleCalendarHandler;
import br.edu.ifba.inf011.model.notificador.Notificador;
import br.edu.ifba.inf011.model.notificador.Strategy.Prior5AndNowStrategy;
import br.edu.ifba.inf011.model.notificador.WhatsappHandler;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public class Aplicacao extends AplicacaoCalendario {

    @Override
    protected Calendario createCalendario() {
        try {
            return new OnlineCalendarioDecorator(
                    new OnlineCalendarioDecorator(
                            new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024),
                            "http://feriados.com"),
                    "http://detroitlions.com");
        } catch (Exception ex) {
            return new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024);
        }
    }

    public void run() throws Exception {

        DatabaseAcademico database = new DatabaseAcademico();

        this.adicionarEvento(((BuilderPartida) Partida.builder()
                .setInicio(LocalDateTime.of(1970, 6, 21, 12, 0))
                .setLocalizacao(new Geolocalizacao("Estádio Azteca - Cidade do México")))
                .setPlacar(4, 1)
                .build("Final da Copa do Mundo de 1970", new Equipe("Brasil"), new Equipe("Italia")));

        this.adicionarEvento(Lembrete.builder()
                .setInicio(LocalDateTime.of(2025, 02, 03, 19, 30))
                .build(TipoEvento.LEMBRETE, "Avaliação II de Padrões de Projeto"));

        this.adicionarEvento(Lembrete.builder()
                .setInicio(LocalDateTime.now())
                .build(TipoEvento.LEMBRETE, "Reunião Semanal"));

        this.adicionarEvento(Lembrete.builder()
                .setInicio(LocalDateTime.of(2025, 02, 28, 19, 30))
                .build(TipoEvento.LEMBRETE, "Avaliação III de Padrões de Projeto"));

        this.adicionarEvento(new AulaObjectAdapter(database.getAula("INF011")));

        this.adicionarEvento(Lembrete.builder().setInicio(LocalDateTime.now()).setPrioridade(10).build(TipoEvento.LEMBRETE, "Evento de Prioridade 10 hoje"));
        this.adicionarEvento(Lembrete.builder().setInicio(LocalDateTime.now().minus(1, ChronoUnit.DAYS)).setPrioridade(5).build(TipoEvento.LEMBRETE, "Evento prioridade 5, ontem"));
        this.adicionarEvento(Lembrete.builder().setInicio(LocalDateTime.now()).setPrioridade(5).build(TipoEvento.LEMBRETE, "Evento prioridade 5, hoje"));
        this.adicionarEvento(Lembrete.builder().setInicio(LocalDateTime.now()).setPrioridade(10).build(TipoEvento.LEMBRETE, "Evento prioridade 10, hoje"));
        this.adicionarEvento(Lembrete.builder().setInicio(LocalDateTime.now().minus(2, ChronoUnit.DAYS)).setPrioridade(10).build(TipoEvento.LEMBRETE, "Evento prioridade 1, anteontem"));

        Collection<Evento> hoje = this.today();

        Notificador notify = new GoogleCalendarHandler(
                new WhatsappHandler(List.of(new Prior5AndNowStrategy()), new EmailHandler()
                ));

        for (Evento e : hoje) {
            System.out.println(e.getDescricao());
            notify.Notificar(e);
            System.out.println("\n\nNotificação do evento finalizada\n\n------------------------------------------------");
        }

    }

    public static void main(String[] args) throws Exception {
        (new Aplicacao()).run();
    }

}
