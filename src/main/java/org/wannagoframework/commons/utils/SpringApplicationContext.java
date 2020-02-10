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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-05-10
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {

  private static ApplicationContext context;

  public static <T> T getBean(Class<T> clazz) {
    return context.getBean(clazz);
  }

  public static Object getBean(String name) {
    return context.getBean(name);
  }

  public void setApplicationContext(final ApplicationContext context)
      throws BeansException {
    SpringApplicationContext.context = context;
  }
}
