package org.ratpackframework.session.internal;

import org.ratpackframework.guice.InjectionContext;
import org.ratpackframework.routing.Exchange;
import org.ratpackframework.routing.Handler;
import org.ratpackframework.session.SessionManager;

public class SessionBindingHandler implements Handler {

  private final Handler delegate;

  public SessionBindingHandler(Handler delegate) {
    this.delegate = delegate;
  }

  @Override
  public void handle(Exchange exchange) {
    InjectionContext injectionContext = exchange.getContext().require(InjectionContext.class);
    SessionManager sessionManager = injectionContext.getInjector().getInstance(SessionManager.class);
    ExchangeSessionManager exchangeSessionManager = new ExchangeSessionManager(exchange, sessionManager);
    exchange.nextWithContext(exchangeSessionManager.getSession(), delegate);
  }

}