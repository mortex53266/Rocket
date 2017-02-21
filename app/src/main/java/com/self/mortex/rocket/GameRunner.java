package com.self.mortex.rocket;

/**
 * Created by mortx1 on 20/02/2017.
 */

public class GameRunner extends Thread {

  private Game game;
  private volatile boolean running = true;

  public GameRunner(Game game) {
    this.game = game;
  }

  @Override
  public void run() {
    game.init();

    long lastTime = System.currentTimeMillis();

    while (running) {

      long now = System.currentTimeMillis();
      long elapsed = now - lastTime;

      if (elapsed < 100) {
        game.update(elapsed);
        game.draw();
      }

      lastTime = now;
    }
  }

  public void shutDown() {
    running = false;
  }
}
