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

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.wannagoframework.commons.utils.OrikaBeanMapper;
import org.wannagoframework.dto.utils.Page;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-06-05
 */
@Component
public class CommonsConverter {

  private final OrikaBeanMapper orikaBeanMapper;

  public CommonsConverter(OrikaBeanMapper orikaBeanMapper) {
    this.orikaBeanMapper = orikaBeanMapper;
  }

  @Bean
  public void commonsConverters() {
    orikaBeanMapper.addMapper(Pageable.class, org.wannagoframework.dto.utils.Pageable.class);
    orikaBeanMapper.addMapper(org.wannagoframework.dto.utils.Pageable.class, Pageable.class);

    orikaBeanMapper.addMapper(Point.class, org.wannagoframework.dto.utils.Point.class);
    orikaBeanMapper.addMapper(org.wannagoframework.dto.utils.Point.class, Point.class);

    orikaBeanMapper.addMapper(PageImpl.class, Page.class);
    orikaBeanMapper.addMapper(Page.class, PageImpl.class);
  }
}