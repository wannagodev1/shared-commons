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

/**
 * Represents coordinates given in Degrees Minutes decimal-seconds (D M s) format
 *
 * @author rgallet
 */
public class DMSCoordinate extends Coordinate {

  public final double wholeDegrees, minutes, seconds;

  DMSCoordinate(double wholeDegrees, double minutes, double seconds) {
    this.wholeDegrees = wholeDegrees;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  @Override
  double degrees() {
    double decimalDegrees = abs(wholeDegrees) + minutes / 60 + seconds / 3600;

    if (wholeDegrees < 0) {
      decimalDegrees = -decimalDegrees;
    }

    return decimalDegrees;
  }

  /**
   * @return minutes
   * @deprecated use minutes
   */
  @Deprecated
  public double getMinutes() {
    return minutes;
  }

  /**
   * @return wholeDegrees
   * @deprecated use wholeDegrees
   */
  @Deprecated
  public double getWholeDegrees() {
    return wholeDegrees;
  }

  /**
   * @return seconds
   * @deprecated use seconds
   */
  @Deprecated
  public double getSeconds() {
    return seconds;
  }

  @Override
  public String toString() {
    return "DMSCoordinate{" +
        "wholeDegrees=" + wholeDegrees +
        ", minutes=" + minutes +
        ", seconds=" + seconds +
        '}';
  }
}
