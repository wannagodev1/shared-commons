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


package org.wannagoframework.commons.security;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-03-07
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  public Optional<String> getCurrentAuditor() {
    String currentUsername = "Unknown";
    try {
      RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
      if (requestAttributes instanceof ServletRequestAttributes) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes)
            .getRequest();
        currentUsername = servletRequest.getHeader("X-SecUsername");
      } else {
        currentUsername = SecurityUtils.getUsername();
      }
    } catch (java.lang.IllegalStateException e) {
    }
    return Optional.ofNullable(currentUsername);
  }
}