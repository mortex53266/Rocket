package com.self.mortex.rocket;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by mortx1 on 20/02/2017.
 */

public class Game {

  private State state = State.PAUSED;
  private SurfaceHolder holder;
  private Resources resources;
  private Ball ball;
  private Bat player;
  private Bat opponent;
  private Paint textPaint;

  public Game(int width, int height, SurfaceHolder holder, Resources resources) {
    this.resources = resources;
    this.holder = holder;

    ball = new Ball(width, height);
    player = new Bat(width, height, Bat.Position.LEFT);
    opponent = new Bat(width, height, Bat.Position.RIGHT);

    textPaint = new Paint();
    textPaint.setTextAlign(Paint.Align.CENTER);
    textPaint.setAntiAlias(true);
    textPaint.setColor(Color.BLUE);
    textPaint.setTextSize(60);
    textPaint.setTypeface(Typeface.DEFAULT_BOLD);

  }

  public void init() {

    Bitmap ballImage = BitmapFactory.decodeResource(resources, R.drawable.ball);
    Bitmap batImage = BitmapFactory.decodeResource(resources, R.drawable.baseballbat);
    ball.init(ballImage);
    player.init(batImage);
    opponent.init(batImage);

  }

  public void update(long elapsed) {
    if (state == State.RUNNING) {
      updateGame(elapsed);
    }
  }

  private void updatePaused() {

  }

  private void initObjectPosition(){
    ball.initPosition();
    player.initPositin();
    opponent.initPositin();
  }

  private void updateGame(long elapsed) {

    if (player.getScreenRect()
        .contains(ball.getScreenRect().left, ball.getScreenRect().centerY())) {
      ball.moveRight();
    } else if (opponent.getScreenRect()
        .contains(ball.getScreenRect().right, ball.getScreenRect().centerY())) {
      ball.moveLeft();
    } else if (ball.getScreenRect().left < player.getScreenRect().right) {
      state = State.LOST;
      initObjectPosition();
    } else if (ball.getScreenRect().right > opponent.getScreenRect().left) {
      state = State.WON;
      initObjectPosition();

    }

    ball.update(elapsed);
    opponent.update(elapsed, ball);
  }

  private void drawText(Canvas canvas, String text) {

    canvas.drawText(text, canvas.getWidth() / 2, canvas.getHeight() / 2, textPaint);
  }

  public void draw() {
    Canvas canvas = holder.lockCanvas();

    if (canvas != null) {
      canvas.drawColor(Color.WHITE);

      switch (state) {
        case LOST:
          drawText(canvas, "You lost :(");
          break;
        case PAUSED:
          drawText(canvas, "Tap Screen to start...");
          break;
        case RUNNING:
          drawGame(canvas);
          break;
        case WON:
          drawText(canvas, "You Won :)");
          break;
      }

      holder.unlockCanvasAndPost(canvas);
    }
  }

  private void drawGame(Canvas canvas) {

    ball.draw(canvas);
    player.draw(canvas);
    opponent.draw(canvas);
  }

  public void onTouchEvent(MotionEvent event) {
    if (state == State.RUNNING) {
      player.setPosition(event.getY());
    } else {
      state = State.RUNNING;
    }
  }

  private enum State {
    PAUSED, WON, LOST, RUNNING
  }
}
