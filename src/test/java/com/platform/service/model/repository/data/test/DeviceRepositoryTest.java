/**
 * Copyright © Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data.test;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.platform.bom.domain.Device;
import com.platform.bom.domain.DeviceType;
import com.platform.service.model.repository.Application;
import com.platform.service.model.repository.data.DeviceRepository;
import com.platform.service.model.repository.data.DeviceTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceRepositoryTest {

	@Autowired
	DeviceRepository repository;

	@Autowired
	DeviceTypeRepository deviceTypeRepository;

	@Test
	public void test1_SaveDevice() {
		Device device = new Device();
		device.setCreatedDate(new Date());
		device.setUpdatedDate(new Date());
		device.setDeviceCode("15000011");
		device.setDeviceSlNo("ESDAA06871");
		device.setFirmwareVersion("1.3");
		device.setManufacturerName("Dell");
		device.setDescription("Tablet");
		device.setRegisteredBy("System");
		device.setStatus(10);
		DeviceType deviceType = new DeviceType();
		deviceType.setCreatedDate(new Date());
		deviceType.setDeviceTypeCode("TAB");
		deviceType.setDeviceTypeName("TABLET");
		device.setDeviceType(deviceType);
		device = repository.save(device);
		Assert.notNull(device.getId());
	}

	@Test
	public void test2_FindAll() {
		Iterable<Device> results = repository.findAll();
		Assert.isTrue(results.iterator().hasNext());
	}

	@Test
	public void test3_FindBy() {
		List<Device> results = repository.findByDeviceCode("15000011");
		Assert.isTrue(results.iterator().hasNext());
	}

	@Test
	public void test8_DeleteAll() {
		repository.deleteAll();
		deviceTypeRepository.deleteAll();
	}

}
