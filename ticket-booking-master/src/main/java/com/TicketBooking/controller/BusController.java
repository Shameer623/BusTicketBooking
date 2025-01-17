package com.TicketBooking.controller;

import com.TicketBooking.payload.BusDto;
import com.TicketBooking.payload.BusnewDto;
import com.TicketBooking.service.BusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
    private BusServiceImpl busService;
@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addBus(@RequestBody BusDto busDto){
        return busService.addBus(busDto);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBus(@PathVariable Long id){
        busService.deleteBus(id);
        return new ResponseEntity<>("Bus Deleted by : "+ id,HttpStatus.OK);
    }

    @GetMapping("/allBus")
    public ResponseEntity<?> ShowBuses(){
        List<BusDto> busdtos = busService.allBus();
        return new ResponseEntity<>(busdtos,HttpStatus.OK);
    }
    @GetMapping("/{busId}/getBus")
    public ResponseEntity<?> getABus(@PathVariable long busId){
        BusDto dto = busService.getBusById(busId);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping("/update")
    public ResponseEntity<?> updateBus(@RequestParam long busId,@RequestBody BusnewDto busDto){
        BusnewDto dto = busService.updateBus(busId,busDto);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
}
