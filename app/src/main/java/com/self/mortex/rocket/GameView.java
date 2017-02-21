package com.self.mortex.rocket;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mortx1 on 19/02/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  private Bitmap ball;

  private GameRunner runner;

  private Game game;

  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);

    SurfaceHolder holder = getHolder();

    holder.addCallback(this);


  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    game.onTouchEvent(event);
    return true;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    game = new Game(getWidth(),getHeight(),holder,getResources());
    runner = new GameRunner(game);
    runner.start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    if (runner != null) {
      runner.shutDown();

      while (runner != null) {
        try {
          runner.join();
          runner = null;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
