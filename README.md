## Padrões de Projeto Utilizados

- **Chain of Responsibility**
- **Template Method**
- **Strategy**
- **Factory Method** (apenas para facilitar a criação da cadeia de handlers)

---

## Descrição dos Padrões

### Chain of Responsibility
O padrão **Chain of Responsibility** foi utilizado para compor uma cadeia de notificadores, onde cada handler representa um tipo de envio (ex.: Google Calendar, e-mail, WhatsApp).

- Cada handler:
    - Verifica se deve ou não processar o evento.
    - Formata a mensagem, se necessário.
    - Executa o envio.
    - Passa o controle para o próximo handler da cadeia, se existir.

### Handlers da cadeia:
- `GoogleCalendarHandler`
- `EmailHandler`
- `WhatsappHandler`

Todos herdam da classe base `AbstractNotifyHandler`, que implementa a interface `Notificador`.

---

### Template Method
O padrão **Template Method** foi aplicado para definir a estrutura base de cada handler da cadeia.

- A classe base (`AbstractNotifyHandler`) define a sequência do fluxo de notificação.
- Métodos principais:
    - `notificar()`: fluxo principal de execução.
    - `devoNotificar()`: valida se o handler deve ser executado.
    - `doNotificar()`: método abstrato, implementado por cada handler específico.
    - `formatar()`: método hook para formatação da mensagem (padrão: descrição do evento).

---

### Strategy
O padrão **Strategy** foi utilizado para encapsular as regras que determinam se cada handler deve ou não notificar.

- Cada handler recebe uma lista de estratégias.
- No método `devoNotificar()`, o handler executa todas as estratégias e, se alguma retornar `true`, a notificação é autorizada.

### Estratégias implementadas:
- `Prior10AndNowStrategy`
- `Prior5AndNowStrategy`
- `Prior1AndTodayTo2DaysAgo`

Todas implementam a interface `RegrasEnvioStrategy`.

---

### Factory Method
O padrão **Factory Method** foi utilizado apenas para facilitar a criação da cadeia de notificadores.

- Centraliza a criação e o encadeamento dos handlers.
- Não tem relação direta com a lógica de notificação, apenas organiza a construção da cadeia.

---

## Estrutura da implemetação

```text
/src
  /notificador
    /Strategy
      Prior1AndTodayTo2DaysAgo.java
      Prior5AndNowStrategy.java
      Prior10AndNowStrategy.java
      RegrasEnvioStrategy.java
    AbstractNotifyHandler.java
    EmailHandler.java
    GoogleCalendarHandler.java
    Notificador.java
    WhatsappHandler.java
    /factory
      NotificadorFactory.java
