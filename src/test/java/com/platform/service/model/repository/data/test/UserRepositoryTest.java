/**
 * Copyright © Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository.data.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.platform.bom.domain.User;
import com.platform.service.model.repository.Application;
import com.platform.service.model.repository.data.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void test1_SaveUser() {
		User user = null;
		user = saveUser("One Platform", "Altimetrik");
		Assert.notNull(user.getId());
		Assert.isTrue(repository.findByLastName("Altimetrik").iterator().hasNext());
		saveUser("Dev", "Altimetrik");
		saveUser("QA", "Altimetrik");
		saveUser("Master", "Altimetrik");
	}

	private User saveUser(String firstName, String lastName) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user = repository.save(user);
		return user;
	}

	@Test
	public void test2_FindAll() {
		Iterable<User> results = repository.findAll();
		Assert.isTrue(results.iterator().hasNext());
	}

	@Test
	public void test3_FindStartsWith() {
		Pageable pageable = new PageRequest(0, 3);
		Page<User> results = repository.findByLastNameStartsWith("Altimetrik", pageable);
		Assert.isTrue(results.getSize() == 3);
	}

	@Test
	public void test4_FindBySort() {
		Sort sort = new Sort(Sort.Direction.ASC, "firstName");
		List<User> results = repository.findByLastNameOrderByFirstNameAsc("Altimetrik", sort);
		Assert.isTrue(results.size() == 4);
		Assert.isTrue(results.iterator().next().getFirstName().equals("Dev"));
		sort = new Sort(Sort.Direction.DESC, "firstName");
		results = repository.findByLastNameOrderByFirstNameDesc("Altimetrik", sort);
		Assert.isTrue(results.iterator().next().getFirstName().equals("QA"));
	}

	@Test
	public void test5_ValidateCache() {
		Cache usersCache = this.cacheManager.getCache("usersCache");
		Assert.notNull(usersCache);
		usersCache.clear();
		Assert.isTrue(repository.findByFirstName("Dev").iterator().hasNext());
		User devUser = this.repository.findByFirstName("Dev").get(0);
		Assert.notNull(usersCache.get("Dev").get());
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) usersCache.get("Dev").get();
		Assert.isTrue(list.get(0).equals(devUser));
	}

	@Test
	public void test8_DeleteId() {
		User user = repository.findByLastName("Altimetrik").iterator().next();
		Assert.notNull(user.getId());
		repository.delete(user.getId());
		Assert.isTrue(repository.findByLastName("Altimetrik").size() != 4);
		List<User> users = repository.findByLastName("Altimetrik");
		for (User user1 : users) {
			repository.delete(user1.getId());
		}
	}

}
