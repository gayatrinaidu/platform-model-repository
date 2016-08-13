/**
 * Copyright (C) Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.platform.bom.domain.TerminalDeviceMapping;

/**
 * TerminalDeviceMappingRepository Interface.
 * @author Harish Mangala
 */
public interface TerminalDeviceMappingRepository extends PagingAndSortingRepository<TerminalDeviceMapping, Long> {

}
