package com.TicketBooking.Repository;

import com.TicketBooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Long> {
    boolean existsByBusNo(String busNo);
}
