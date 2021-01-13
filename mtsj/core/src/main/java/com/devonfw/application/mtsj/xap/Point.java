package com.devonfw.application.mtsj.xap;

/**
 *
 * Class used to create Points for the calculation of the ConcaveHull.
 *
 */
public class Point {

  private final Double x;

  private final Double y;

  public Point(Double x, Double y) {

    this.x = x;
    this.y = y;
  }

  public Double getX() {

    return this.x;
  }

  public Double getY() {

    return this.y;
  }

  @Override
  public String toString() {

    return "(" + this.x + " " + this.y + ")";
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof Point) {
      if (this.x.equals(((Point) obj).getX()) && this.y.equals(((Point) obj).getY())) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {

    // http://stackoverflow.com/questions/22826326/good-hashcode-function-for-2d-coordinates
    // http://www.cs.upc.edu/~alvarez/calculabilitat/enumerabilitat.pdf
    int tmp = (int) (this.y + ((this.x + 1) / 2));
    return Math.abs((int) (this.x + (tmp * tmp)));
  }
}