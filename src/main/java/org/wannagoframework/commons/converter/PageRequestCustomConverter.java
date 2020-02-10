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

import java.util.stream.Collectors;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.wannagoframework.dto.utils.Pageable;
import org.wannagoframework.dto.utils.Pageable.Order.Direction;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 2019-06-05
 */
@Component
public class PageRequestCustomConverter extends
    BidirectionalConverter<Pageable, PageRequest> {

  @Override
  public PageRequest convertTo(Pageable source,
      Type<PageRequest> destinationType, MappingContext context) {
    Sort s = Sort.by(source.getSort().stream().map(
        order -> order.getDirection().equals(Direction.ASC) ? Order.asc(order.getProperty())
            : Order.desc(order.getProperty())).collect(
        Collectors.toList()));

    return PageRequest.of(source.getPage(), source.getSize(), s);
  }

  @Override
  public Pageable convertFrom(PageRequest source,
      Type<Pageable> destinationType, MappingContext context) {
    return Pageable.of(source.getPageNumber(),
        source.getPageSize(),
        source.getSort().stream().map(
            order -> order.isAscending() ? Pageable.Order.asc(order.getProperty())
                : Pageable.Order.desc(order.getProperty())).collect(Collectors.toList()));
  }
}
