/**
 * Copyright © Altimetrik 2016. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Altimetrik. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Altimetrik.
 */

package com.platform.service.model.repository;

import org.apache.log4j.Logger;

import org.springframework.boot.SpringApplication;

import com.platform.integration.neo4j.Neo4jApplication;

/**
 * Class for code based configuration of Spring managed Neo4j infrastructure.
 * <p>
 * Subclasses are required to provide an implementation of graphDbService ....
 * @author Harish Mangala
 */
public class Application extends Neo4jApplication {
	Logger log = Logger.getLogger(Application.class);

	/**
	 * Application Constructor to load base packages.
	 */
	public Application() {
		super("com.platform");
	}

	/**
	 * bootRun.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		new SpringApplication(Application.class).run(args);
	}

}
