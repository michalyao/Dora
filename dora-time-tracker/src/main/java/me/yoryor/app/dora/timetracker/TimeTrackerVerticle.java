package me.yoryor.app.dora.timetracker;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import me.yoryor.app.dora.common.MicroServiceVerticle;

import java.util.function.Function;

public class TimeTrackerVerticle extends MicroServiceVerticle {

  private static final String DROP_STATEMENT = "DROP TABLE IF EXISTS TACKER";
  private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS TRACKER (id INTEGER IDENTITY, event VARCHAR(250), start Date)";
  private static final String INSERT_STATEMENT = "INSERT INTO TRACKER (event, start) VALUES ? ?";
  private static final String SELECT_STATEMENT = "SELECT * FROM TRACKER BY ID DESC LIMIT 10";

  private JDBCClient jdbc;

  @Override
  public void start() {
    super.start();
    jdbc = JDBCClient.createNonShared(vertx, config());
  }


  private Future<Void> iniializeDB(boolean drop) {
    Future<Void> dbReady = Future.future();
    Future<SQLConnection> connectionRetrived = Future.future();
    jdbc.getConnection(connectionRetrived.completer());

    Function<SQLConnection, Future<SQLConnection>> dropTable = sqlConnection -> {
      Future<SQLConnection> future = Future.future();
      if (!drop) {
        future.complete(sqlConnection);
      } else {
        sqlConnection.execute(DROP_STATEMENT, completer(future, sqlConnection));
      }
      return future;
    };

    Function<SQLConnection, Future<Void>> createTable = sqlConnection -> {
      Future<Void> future = Future.future();
      sqlConnection.execute(CREATE_TABLE_STATEMENT, future.completer());
      return future;
    };

  return null;
  }

  private static Handler<AsyncResult<Void>> completer(Future<SQLConnection> future, SQLConnection sqlConnection) {
    return ar -> {
      if (ar.failed()) {
        future.fail(ar.cause());
      } else {
        future.complete(sqlConnection);
      }
    };
  }
}

