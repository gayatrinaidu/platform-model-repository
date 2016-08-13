/**
 * Copyright (C) Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data.test;

import java.util.Date;

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
import com.platform.bom.domain.Terminal;
import com.platform.bom.domain.TerminalDeviceMapping;
import com.platform.bom.domain.TerminalType;
import com.platform.service.model.repository.Application;
import com.platform.service.model.repository.data.DeviceRepository;
import com.platform.service.model.repository.data.DeviceTypeRepository;
import com.platform.service.model.repository.data.TerminalDeviceMappingRepository;
import com.platform.service.model.repository.data.TerminalRepository;
import com.platform.service.model.repository.data.TerminalTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalDeviceRepositoryTest {

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	DeviceTypeRepository deviceTypeRepository;

	@Autowired
	TerminalRepository terminalRepository;

	@Autowired
	TerminalTypeRepository terminalTypeRepository;

	@Autowired
	TerminalDeviceMappingRepository repository;

	@Test
	public void test1_SaveTerminalDevice() {
		Device device = createDevice();
		Assert.notNull(device.getId());
		Terminal terminal = createTerminal();
		Assert.notNull(terminal.getId());
		TerminalDeviceMapping terminalDeviceMapping = new TerminalDeviceMapping();
		terminalDeviceMapping.setDevice(device);
		terminalDeviceMapping.setTerminal(terminal);
		terminalDeviceMapping.setCreatedDate(new Date());
		terminalDeviceMapping = repository.save(terminalDeviceMapping);
		Assert.notNull(terminalDeviceMapping.getId());
	}

	private Terminal createTerminal() {
		Terminal terminal = new Terminal();
		terminal.setCreatedDate(new Date());
		terminal.setUpdatedDate(new Date());
		terminal.setTerminalCode("16118002");
		terminal.setStatus(10);
		TerminalType terminalType = new TerminalType();
		terminalType.setCreatedDate(new Date());
		terminalType.setTerminalTypeName("FI-Tab-FP");
		terminal.setTerminalType(terminalType);
		return terminalRepository.save(terminal);
	}

	private Device createDevice() {
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
		return deviceRepository.save(device);
	}

	@Test
	public void test8_DeleteAll() {
		repository.deleteAll();
		terminalRepository.deleteAll();
		terminalTypeRepository.deleteAll();
		deviceRepository.deleteAll();
		deviceTypeRepository.deleteAll();
	}

}
