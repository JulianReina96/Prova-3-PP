package br.edu.ifba.inf011.model.notificador;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.notificador.Strategy.RegrasEnvioStrategy;
import java.util.List;

/*
 *  Template Method
 */
public abstract class AbstractNotifyHandler implements Notificador {

    private Notificador proximo;
    private List<RegrasEnvioStrategy> regras;

    public AbstractNotifyHandler(List<RegrasEnvioStrategy> regras, Notificador proximo) {
        this.regras = regras;
        this.proximo = proximo;
    }

    public AbstractNotifyHandler(List<RegrasEnvioStrategy> regras) {
        this(regras, null);
    }

    public void setProximo(Notificador proximo) {
        this.proximo = proximo;
    }

    public void setRegras(List<RegrasEnvioStrategy> regras) {
        this.regras = regras;
    }

    @Override
    public final void Notificar(Evento evento) {
        if (this.devoNotificar(evento)) {
            String mensagemFormatada = this.formatar(evento);
            this.doNotificar(mensagemFormatada);
        }

        if (proximo != null) {
            proximo.Notificar(evento);
        }

    }

    public final boolean devoNotificar(Evento evento) {
        for (var regra : this.regras) {
            if (regra.verificar(evento)) {
                return true;
            }
        }

        return false;
    }

    public abstract void doNotificar(String mensagem);

    public String formatar(Evento evento) {
        return evento.getDescricao();
    }

}
