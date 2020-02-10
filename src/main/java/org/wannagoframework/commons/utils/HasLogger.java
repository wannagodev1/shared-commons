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

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.wannagoframework.commons.security.SecurityUtils;
import org.wannagoframework.dto.utils.AppContextThread;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-03-26
 */
public interface HasLogger {

  default String getLoggerPrefix(final String methodName) {
    String username = AppContextThread.getCurrentUsername() == null ? SecurityUtils.getUsername(): AppContextThread.getCurrentUsername();
    String sessionId = AppContextThread.getCurrentSessionId() == null ? "local": AppContextThread.getCurrentSessionId();
    MDC.put("wannaplay.username", username );
    MDC.put("wannaplay.sessionId", sessionId);
    String params = "";
    if (StringUtils.isNotBlank(username)) {
      params += username;
    }
    /*
    if (StringUtils.isNotBlank(sessionId)) {
      params += params.length() > 0 ? ", " + sessionId : sessionId;
    }
     */
    return String.format("%-30s", methodName + "(" + params + ")") + " :: ";
  }

  default String getLoggerPrefix(final String methodName, Object... _params) {
    String username = AppContextThread.getCurrentUsername() == null ? SecurityUtils.getUsername(): AppContextThread.getCurrentUsername();
    String sessionId = AppContextThread.getCurrentSessionId() == null ? "local": AppContextThread.getCurrentSessionId();
    MDC.put("wannaplay.username", username );
    MDC.put("wannaplay.sessionId", sessionId);
    StringBuilder params = new StringBuilder();
    if (StringUtils.isNotBlank(username)) {
      params.append(username).append(_params.length > 0 ? ", " : "");
    }
    /*
    if (StringUtils.isNotBlank(sessionId)) {
      params.append( params.length() > 0 ? ", " + sessionId : sessionId );
    }
     */
    if (_params.length > 0) {
      for (Object p : _params) {
        if (p.getClass().isArray()) {
          params.append(Arrays.asList((Object[]) p)).append(", ");
        } else {
          params.append(p).append(", ");
        }
      }
      params = new StringBuilder(params.substring(0, params.length() - 2));
    }
    return String.format("%-30s", methodName + "(" + params + ")") + " :: ";
  }

  default Logger logger() {
    return LoggerFactory.getLogger(getClass());
  }

  default Logger logger(Class aClass) {
    return LoggerFactory.getLogger(aClass);
  }
}
