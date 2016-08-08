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

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.platform.bom.domain.User;

/**
 * UserTypeRepository Interface.
 * @author Harish Mangala
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@CacheConfig(cacheNames = "usersCache")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	/**
	 * Search by Last Name.
	 * @param lastname String
	 * @return List of users
	 */
	List<User> findByLastName(@Param("lastname") String lastname);

	/**
	 * Search by First Name.
	 * @param firstname String
	 * @return List of users
	 */
	@Cacheable
	List<User> findByFirstName(@Param("firstname") String firstname);

	/**
	 * Search byLike Last Name.
	 * @param lastname String
	 * @param pageable Pageable
	 * @return Page of users
	 */
	Page<User> findByLastNameStartsWith(@Param("lastname") String lastname, Pageable pageable);

	/**
	 * Search By Last Name and Order by First Name Ascending.
	 * @param lastname String
	 * @param sort Sort
	 * @return List of users
	 */
	List<User> findByLastNameOrderByFirstNameAsc(@Param("lastname") String lastname, Sort sort);

	/**
	 * Search By Last Name and Order by First Name Descending.
	 * @param lastname String
	 * @param sort Sort
	 * @return List of users
	 */
	List<User> findByLastNameOrderByFirstNameDesc(@Param("lastname") String lastname, Sort sort);
}
