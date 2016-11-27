package me.yoryor.app.dora.timetracker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import me.yoryor.app.dora.timetracker.util.TimeUtil;

import java.time.LocalDateTime;

public class TemporalVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(TemporalVerticle.class);

  @Override
  public void start() {
    HttpServer httpServer = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route(HttpMethod.GET, "/now").handler(routingContext -> {
      routingContext.response().end(TimeUtil.format(LocalDateTime.now()));
      LOG.info("handle a request");
    });

    router.routeWithRegex(".*foo").handler(routingContext -> {
      routingContext.response().end(routingContext.request().path());
    });

   httpServer.requestHandler(router::accept).listen(8888);
    LOG.info("http start");
  }

}
