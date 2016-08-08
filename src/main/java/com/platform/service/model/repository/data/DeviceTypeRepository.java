/**
 * Copyright © Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.platform.bom.domain.DeviceType;

/**
 * DeviceTypeRepository Interface.
 * @author Harish Mangala
 */
@RepositoryRestResource(collectionResourceRel = "deviceType", path = "deviceType")
public interface DeviceTypeRepository extends PagingAndSortingRepository<DeviceType, Long> {

}
