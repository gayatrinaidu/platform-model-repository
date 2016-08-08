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

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.platform.bom.domain.Terminal;
import com.platform.bom.domain.TerminalType;
import com.platform.service.model.repository.Application;
import com.platform.service.model.repository.data.TerminalRepository;
import com.platform.service.model.repository.data.TerminalTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalRepositoryTest {
	@Autowired
	TerminalRepository repository;

	@Autowired
	TerminalTypeRepository terminalTypeRepository;

	@Test
	public void test1_SaveTerminal() {
		Terminal terminal = new Terminal();
		terminal.setCreatedDate(new Date());
		terminal.setUpdatedDate(new Date());
		terminal.setTerminalCode("16118002");
		terminal.setStatus(10);
		TerminalType terminalType = new TerminalType();
		terminalType.setCreatedDate(new Date());
		terminalType.setTerminalTypeName("FI-Tab-FP");
		terminal.setTerminalType(terminalType);
		terminal = repository.save(terminal);
		Assert.notNull(terminal.getId());
	}

	@Test
	public void test8_DeleteAll() {
		repository.deleteAll();
		terminalTypeRepository.deleteAll();
	}

}
