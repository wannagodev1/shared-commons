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


package org.wannagoframework.commons.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import org.wannagoframework.commons.utils.HasLogger;

public class DateConverter {

  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
  private static final SimpleDateFormat sdfIso8601 = new SimpleDateFormat(ISO8601_DATE_FORMAT);

  public static class Serialize extends JsonSerializer<Instant> implements HasLogger {

    @Override
    public void serialize(Instant value, JsonGenerator jgen, SerializerProvider provider) {
      String loggerPrefix = getLoggerPrefix("serialize");
      try {
        if (value == null) {
          jgen.writeNull();
        } else {
          jgen.writeString(DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault())
              .format(value));
        }
      } catch (Exception e) {
        logger().error(
            loggerPrefix + "Unexpected error while serializing date : '" + value + "' : " + e
                .getMessage(), e);
      }
    }
  }

  public static class Deserialize extends JsonDeserializer<Instant> implements HasLogger {

    @Override
    public Instant deserialize(com.fasterxml.jackson.core.JsonParser jp,
        DeserializationContext ctxt) throws IOException {
      String loggerPrefix = getLoggerPrefix("deserialize");
      String dateAsString = "";
      try {
        dateAsString = jp.getText();
        if (StringUtils.isBlank(dateAsString)) {
          return null;
        } else {
          try {
            return Instant.ofEpochMilli(sdf1.parse(dateAsString).getTime());
          } catch (NumberFormatException | ParseException pe) {
            return Instant.ofEpochMilli(sdfIso8601.parse(dateAsString).getTime());
          }
        }
      } catch (Exception e) {
        logger().error(
            loggerPrefix + "Unexpected error while deserializing date : '" + dateAsString + "' : "
                + e.getMessage(), e);
      }
      return null;
    }
  }
}