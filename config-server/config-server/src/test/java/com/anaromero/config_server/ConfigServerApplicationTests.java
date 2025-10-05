package com.anaromero.config_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootTest
@EnableConfigServer
@EnableDiscoveryClient
class ConfigServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
