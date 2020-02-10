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

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.toRadians;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstraction of coordinate systems (degrees, radians, dms and gps)
 *
 * @author rgallet
 */
abstract public class Coordinate implements Serializable {

  public static DegreeCoordinate fromDegrees(double decimalDegrees) {
    return new DegreeCoordinate(decimalDegrees);
  }

  public static DMSCoordinate fromDMS(double wholeDegrees, double minutes, double seconds) {
    return new DMSCoordinate(wholeDegrees, minutes, seconds);
  }

  public static GPSCoordinate fromGPS(double wholeDegrees, double minutes) {
    return new GPSCoordinate(wholeDegrees, minutes);
  }

  public static RadianCoordinate fromRadians(double radians) {
    return new RadianCoordinate(radians);
  }

  abstract double degrees();

  /**
   * @return degree value
   * @deprecated use degrees()
   */
  @Deprecated
  public double getValue() {
    return degrees();
  }

  DMSCoordinate toDMSCoordinate() {
    double _wholeDegrees = (int) degrees();
    double remaining = abs(degrees() - _wholeDegrees);
    double _minutes = (int) (remaining * 60);
    remaining = remaining * 60 - _minutes;
    double _seconds = new BigDecimal(remaining * 60).setScale(4, RoundingMode.HALF_UP)
        .doubleValue();

    return new DMSCoordinate(_wholeDegrees, _minutes, _seconds);
  }

  DegreeCoordinate toDegreeCoordinate() {
    return new DegreeCoordinate(degrees());
  }

  GPSCoordinate toGPSCoordinate() {
    double _wholeDegrees = floor(degrees());
    double remaining = degrees() - _wholeDegrees;
    double _minutes = floor(remaining * 60);

    return new GPSCoordinate(_wholeDegrees, _minutes);
  }

  RadianCoordinate toRadianCoordinate() {
    return new RadianCoordinate(toRadians(degrees()));
  }
}
