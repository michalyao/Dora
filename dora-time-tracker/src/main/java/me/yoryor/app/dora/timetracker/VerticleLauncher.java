package me.yoryor.app.dora.timetracker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class VerticleLauncher extends AbstractVerticle {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new TemporalVerticle());
  }
}
