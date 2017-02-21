package com.self.mortex.rocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by mortx1 on 20/02/2017.
 */

public class Sprite {
  private float x;
  private float y;

  private int screenWidth;
  private int screenHeight;

  private Bitmap image;

  private Rect bounds;

  public Sprite(int screenWidth, int screenheight) {
    this.screenHeight = screenheight;
    this.screenWidth = screenWidth;

    this.y = 0;
    this.x = 0;

  }

  public void init(Bitmap image) {
    this.image = image;
    bounds = new Rect(0, 0, image.getWidth(), image.getHeight());

  }

  public void update(long elapsed) {

  }

  public void draw(Canvas canvas) {
    canvas.drawBitmap(image, x, y, null);
  }

  public Rect getRect() {
    return bounds;
  }

  public Rect getScreenRect() {

    Rect rect = new Rect((int) x, (int) y, (int) x + getRect().width(), (int) y + getRect().height());

    return rect;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }
}
