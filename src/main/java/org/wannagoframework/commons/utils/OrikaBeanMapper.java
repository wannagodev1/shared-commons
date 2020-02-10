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

import java.util.Map;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-06-06
 */
@Component
public class OrikaBeanMapper extends ConfigurableMapper implements ApplicationContextAware,
    HasLogger {

  private MapperFactory factory;
  private ApplicationContext applicationContext;

  public OrikaBeanMapper() {
    super(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void configure(MapperFactory factory) {
    this.factory = factory;
    addAllSpringBeans(applicationContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
    // Nothing to configure for now
  }

  @SuppressWarnings("rawtypes")
  public void addMapper(Mapper<?, ?> mapper) {
    String loggerString = getLoggerPrefix("addMapper");
    logger().debug(
        loggerString + "Add : " + mapper.getAType().getName() + " -> " + mapper.getBType()
            .getName());

    factory.classMap(mapper.getAType(), mapper.getBType())
        .byDefault()
        .customize((Mapper) mapper)
        .register();
  }

  public void addMapper(Class src, Class dst) {
    String loggerString = getLoggerPrefix("addMapper");
    logger().debug(loggerString + "Add : " + src.getName() + " -> " + dst.getName());

    ClassMapBuilder classMapBuilder = factory.classMap(src, dst);
    classMapBuilder.byDefault()
        .register();
  }

  public ClassMapBuilder getClassMapBuilder(Class src, Class dst) {
    return factory.classMap(src, dst);
  }

  /**
   * Registers a {@link Converter} into the {@link ConverterFactory}.
   */
  public void addConverter(Converter<?, ?> converter) {
    String loggerString = getLoggerPrefix("addConverter");
    logger().debug(
        loggerString + "Add : " + converter.getAType().getName() + " -> " + converter.getBType()
            .getName());
    factory.getConverterFactory().registerConverter(converter);
  }

  /**
   * Scans the appliaction context and registers all Mappers and Converters found in it.
   */
  @SuppressWarnings("rawtypes")
  private void addAllSpringBeans(final ApplicationContext applicationContext) {
    Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
    for (Mapper mapper : mappers.values()) {
      addMapper(mapper);
    }
    Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
    for (Converter converter : converters.values()) {
      addConverter(converter);
    }
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    init();
  }
}
