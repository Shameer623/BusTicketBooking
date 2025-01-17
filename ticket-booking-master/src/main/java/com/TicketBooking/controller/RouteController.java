package com.TicketBooking.controller;

import com.TicketBooking.payload.RouteDto;
import com.TicketBooking.service.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {

    @Autowired
    private RouteServiceImpl routeService ;
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/{BusId}/add")
    public ResponseEntity<?> addRoute(@PathVariable long BusId, @RequestBody RouteDto routeDto){

        return routeService.addRoute(BusId,routeDto);
    }

    @GetMapping("/searchBusFromToLocationDate")
    public ResponseEntity<?> searchBus(@RequestParam String fromLocation,
                                       @RequestParam String toLocation,
                                       @RequestParam String fromDate){
        return routeService.findBus(fromLocation, toLocation, fromDate);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping("/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable long routeId){
       return routeService.deleteRoute(routeId);
    }
}
