/*
 * This file is part of the WannaGo distribution (https://github.com/wannago).
 * Copyright (c) [2019] - [2020].
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package org.wannagoframework.commons.geocalc;

import java.io.Serializable;

/**
 * Represent a point in spherical system
 *
 * @author rgallet
 */
public class Point implements Serializable {

  //decimal degrees
  public final double latitude, longitude;

  private Point(Coordinate latitude, Coordinate longitude) {
    this.latitude = latitude.degrees();
    this.longitude = longitude.degrees();
  }

  /**
   * Create a new Point.
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the point
   */
  public static Point at(Coordinate latitude, Coordinate longitude) {
    return new Point(latitude, longitude);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Point other = (Point) obj;
    if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
      return false;
    }
    return Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(other.longitude);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 31 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (
        Double.doubleToLongBits(this.latitude) >>> 32));
    hash = 31 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (
        Double.doubleToLongBits(this.longitude) >>> 32));
    return hash;
  }

  @Override
  public String toString() {
    return "Point{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
  }
}
