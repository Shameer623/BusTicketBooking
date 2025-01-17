package com.TicketBooking.service;

import com.TicketBooking.Repository.BusRepository;
import com.TicketBooking.Repository.RouteRepository;
import com.TicketBooking.Repository.DriverRepository;
import com.TicketBooking.Repository.SubRouteRepository;
import com.TicketBooking.entity.Bus;

import com.TicketBooking.exception.ResourseNotFoundException;
import com.TicketBooking.payload.BusDto;
import javax.transaction.Transactional;

import com.TicketBooking.payload.BusnewDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl {
    private BusRepository busRepository;
    private DriverRepository driverRepository;

    private RouteRepository routeRepository;
    private SubRouteRepository subRouteRepository;

    private ModelMapper mapper;

    public BusServiceImpl(BusRepository busRepository, DriverRepository driverRepository, RouteRepository routeRepository, SubRouteRepository subRouteRepository, ModelMapper mapper) {
        this.busRepository = busRepository;
        this.driverRepository = driverRepository;
        this.routeRepository = routeRepository;
        this.subRouteRepository = subRouteRepository;
        this.mapper = mapper;
    }

    @Transactional
    public ResponseEntity<?> addBus(BusDto busDto) {

            if(!busRepository.existsByBusNo(busDto.getBusNo())) {
                Bus bus = mapToEntity(busDto);
                Bus saved = busRepository.save(bus);

                return new ResponseEntity<>(mapToDto(saved), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(new ResourseNotFoundException("Bus is already saved !"),HttpStatus.BAD_REQUEST);
            }

    }

    private Bus mapToEntity(BusDto busDto){
        return mapper.map(busDto,Bus.class);
    }
    private BusDto mapToDto(Bus bus){
        return mapper.map(bus,BusDto.class);
    }


    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public List<BusDto> allBus() {
        List<Bus> all = busRepository.findAll();
        return all.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public BusDto getBusById(long busId) {
        Bus bus = busRepository.getReferenceById(busId);
        return mapToDto(bus);
    }

    public BusnewDto updateBus(long busId, BusnewDto busDto) {
        Bus bus = busRepository.getReferenceById(busId);
        bus.setAvailableSeats(busDto.getAvailableSeats());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setPrice(busDto.getPrice());
        busRepository.save(bus);
        busDto.setId(busId);
        return busDto;
    }
    public int updateAvailableSeat(long busId){

        Bus bus = busRepository.findById(busId).orElseThrow(()->new ResourseNotFoundException("Bus is not present"));
        int seats = bus.getAvailableSeats();
        if(seats>0 && seats<bus.getTotalSeats()) {
            bus.getAvailableSeats();
            bus.setAvailableSeats(seats- 1);
            busRepository.save(bus);
            return bus.getAvailableSeats();
        }

        return -1;
    }
}
