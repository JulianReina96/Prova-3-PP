	package br.edu.ifba.inf011.model.notificador;

import java.util.List;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;

public interface Notificador {
	public void Notificar(Evento evento);
	public void setProximo(Notificador proximo);
	public void setRegras(List<RegrasEnvioStrategy> regras);
}
