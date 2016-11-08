package me.yoryor.app.dora.timetracker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * embedded demo
 */
public class HelloWorld extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer().requestHandler(req -> req.response().end("hello world")).listen(8888);
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloWorld());
  }
}
