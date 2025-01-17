package com.TicketBooking.Repository;

import com.TicketBooking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {
    boolean existsByBusId(long busId);
//    boolean existsById()

    List<Route> findByFromLocationAndToLocationAndFromDate(String fromLocation, String toLocation, String fromDate);

}