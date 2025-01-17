package com.TicketBooking;

import com.TicketBooking.service.RouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedbusApplicationTests {

	@Autowired
	RouteServiceImpl routeService;

	@Test
	void contextLoads() {
		routeService.findBus("City A","City B","2024-04-01");
	}

}
