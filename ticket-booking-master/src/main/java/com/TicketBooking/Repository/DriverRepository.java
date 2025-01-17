package com.TicketBooking.Repository;

import com.TicketBooking.entity.BusDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<BusDriver,Long> {
}
