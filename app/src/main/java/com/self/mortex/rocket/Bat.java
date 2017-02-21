package com.self.mortex.rocket;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by mortx1 on 20/02/2017.
 */

public class Bat extends Sprite {

  private static final int margin = 20;
  private Random random = new Random();
  private int direction;
  private Position position;
  private float speed = 0.4f;

  public Bat(int screenWidth, int screenheight, Position position) {
    super(screenWidth, screenheight);

    this.position = position;
  }

  @Override
  public void init(Bitmap image) {
    super.init(image);

    direction = random.nextInt(2) * 2 - 1;

    if (position == Position.LEFT) {
      setX(margin);

    } else if (position == Position.RIGHT) {
      setX((getScreenWidth() - margin ) - getRect().centerX());
    }
  }

  public void initPositin() {
    setY(getScreenHeight() / 2 - getRect().centerY());
  }

  public void setPosition(float y) {
    setY(y - getRect().centerY());
  }

  public void update(long elapsed, Ball ball) {
    int deciotion = random.nextInt(20);

    if (deciotion == 0) {
      direction = 0;
    } else if (deciotion == 1) {
      direction = random.nextInt(2) * 2 - 1;
    } else if (deciotion < 4) {
      if (ball.getScreenRect().centerY() < getScreenRect().centerY()) {
        direction = -1;
      } else {
        direction = 1;
      }
    }

    if (getScreenRect().top < 0) {
      direction = 1;
    } else if (getScreenRect().bottom >= getScreenHeight()) {
      direction = -1;
    }
    float y = getY();

    y += direction * speed * elapsed;

    setY(y);
  }

  public enum Position {
    LEFT, RIGHT
  }

}
