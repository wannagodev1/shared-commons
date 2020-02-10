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


package org.wannagoframework.commons.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.wannagoframework.commons.utils.HasLogger;
import org.wannagoframework.dto.utils.AppContextThread;

@Component
@Order(1)
public class SecurityFilter implements Filter, HasLogger {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    SpringBeanAutowiringSupport
        .processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String loggerPrefix = getLoggerPrefix("doFilter");
    HttpServletRequest req = (HttpServletRequest) request;

    String username = req.getHeader("X-SecUsername");
    if (StringUtils.isNotBlank(username)) {
      logger().trace(loggerPrefix + "Username = " + username);
      AppContextThread.setCurrentUsername(username);
    }
    String securityUserId = req.getHeader("X-SecSecurityUserId");
    if (StringUtils.isNotBlank(securityUserId)) {
      logger().trace(loggerPrefix + "SecurityUserId = " + securityUserId);
      AppContextThread.setCurrentSecurityUserId(securityUserId);
    }
    String sessionId = req.getHeader("X-SecSessionId");
    if (StringUtils.isNotBlank(sessionId)) {
      logger().trace(loggerPrefix + "SessionId = " + sessionId);
      AppContextThread.setCurrentSessionId(sessionId);
    }
    String iso3Language = req.getHeader("X-Iso3Language");
    if (StringUtils.isNotBlank(iso3Language)) {
      logger().trace(loggerPrefix + "Iso3Language = " + iso3Language);
      AppContextThread.setCurrentIso3Language(iso3Language);
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}