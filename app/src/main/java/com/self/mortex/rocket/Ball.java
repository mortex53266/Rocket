package com.self.mortex.rocket;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Created by mortx1 on 20/02/2017.
 */

public class Ball extends Sprite {

  private final float speedX = 0.5f;
  private final float speedY = 0.5f;

  private int directionX ;
  private int directionY ;

  public Ball(int screenWidth, int screenheight) {
    super(screenWidth, screenheight);
  }

  public void update(long elapsed) {

    float x = getX();
    float y = getY();


    Rect screenRect = getScreenRect();

    if (screenRect.left <= 0) {
      directionX = 1;
    } else if (screenRect.right >= getScreenWidth()) {
      directionX = -1;
    }

    if (screenRect.top < 0) {
      directionY = 1;
    } else if (screenRect.bottom >= getScreenHeight()) {
      directionY = -1;
    }


    x += directionX * speedX * elapsed;
    y += directionY * speedY * elapsed;

    Log.d("X is:", String.valueOf(x));

    setX(x);
    setY(y);
  }

  @Override
  public void init(Bitmap image) {
    super.init(image);

    initPosition();

    Random random = new Random();

    directionX = random.nextInt(2)*2 - 1;
    directionY = random.nextInt(2)*2 - 1;
  }

  public void initPosition() {
    setX(getScreenWidth()/2 - getRect().centerX());
    setY(getScreenHeight()/2 - getRect().centerY());
  }

  public void moveRight() {
    directionX = 1;
  }
  public void moveLeft() {
    directionX = -1;
  }
}
