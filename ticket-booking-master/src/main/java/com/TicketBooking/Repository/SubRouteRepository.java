package com.TicketBooking.Repository;

import com.TicketBooking.entity.SubRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRouteRepository extends JpaRepository<SubRoute,Long> {
    List<SubRoute> findByFromLocationAndToLocationAndFromDate(String fromLocation, String toLocation, String fromDate);

    boolean existsByRouteId(long routeId);

    void deleteByRouteId(long routeId);
}
