package com.TicketBooking.controller;

import com.TicketBooking.payload.SubRouteDto;
import com.TicketBooking.service.SubRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subroute")
public class SubRouteController {

    @Autowired
    private SubRouteServiceImpl subRouteService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<?> AddSubRoute(@RequestParam long routeId, @RequestBody List<SubRouteDto> dtos){
        ResponseEntity<?> responseEntity = subRouteService.addAll(routeId, dtos);
        return responseEntity;

    }

}
