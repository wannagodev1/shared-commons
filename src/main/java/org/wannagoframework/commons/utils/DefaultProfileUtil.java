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


package org.wannagoframework.commons.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;

/**
 * Utility class to load a Spring profile to be used as default when there is no {@code
 * spring.profiles.active} set in the environment or as command line argument. If the value is not
 * available in {@code application.yml} then {@code dev} profile will be used as default.
 */
public final class DefaultProfileUtil {

  private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

  private DefaultProfileUtil() {
  }

  /**
   * Set a default to use when no profile is configured.
   *
   * @param app the Spring application.
   */
  public static void addDefaultProfile(SpringApplication app) {
    Map<String, Object> defProperties = new HashMap<>();
    /*
     * The default profile to use when no other profiles are defined
     * This cannot be set in the application.yml file.
     * See https://github.com/spring-projects/spring-boot/issues/1219
     */
    defProperties.put(SPRING_PROFILE_DEFAULT, SpringProfileConstants.SPRING_PROFILE_DEVELOPMENT);
    app.setDefaultProperties(defProperties);
  }
}
