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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * Utility class for working with FreeMarker. This bean is only created if and only if FreeMaker
 * {@link Template} is the classPath.
 *
 * @author Wannago dev1.
 * @version 1.0
 * @since 2019-11-03
 */
@Component
@ConditionalOnClass(freemarker.template.Template.class)
public class FreeMakerProcessor {

  private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

  /**
   * Process the given sourceCode using the given model and return a string as the result of
   * processing.
   *
   * @param sourceCode the source code to be processed.
   * @param model the model to use
   * @return the result as String
   * @see FreeMarkerTemplateUtils#processTemplateIntoString(Template, Object)
   */
  public String process(String sourceCode, Object model) {
    String result = sourceCode;
    try {
      final Template bodyTemplate = new Template(null, sourceCode, cfg);
      result = FreeMarkerTemplateUtils.processTemplateIntoString(bodyTemplate, model);
    } catch (IOException | TemplateException e) {
      e.printStackTrace();
    }
    return result;
  }
}
