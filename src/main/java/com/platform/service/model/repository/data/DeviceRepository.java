/**
 * Copyright © Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.platform.bom.domain.Device;

/**
 * DeviceRepository Interface.
 * @author Harish Mangala
 */
@RepositoryRestResource(collectionResourceRel = "device", path = "device")
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

	/**
	 * Search  Device by Device code.
	 * @param deviceCode String
	 * @return deviceCode
	 * 	List of Device
	 */
	List<Device> findByDeviceCode(@Param("deviceCode") String deviceCode);
}
