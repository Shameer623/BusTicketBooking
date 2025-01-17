package com.TicketBooking.service;

import com.TicketBooking.Repository.RouteRepository;
import com.TicketBooking.Repository.SubRouteRepository;
import com.TicketBooking.entity.SubRoute;
import com.TicketBooking.exception.ResourseNotFoundException;
import com.TicketBooking.payload.SubRouteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubRouteServiceImpl {
    @Autowired
    private SubRouteRepository subRouteRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RouteRepository routeRepository;
    public ResponseEntity<?> addAll(long routeId, List<SubRouteDto> dtos) {
    if(routeRepository.existsById(routeId)){
        List<SubRoute> subRoutes = dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
        for (SubRoute s:subRoutes){
            s.setRouteId(routeId);
        }
        List<SubRoute> list = subRouteRepository.saveAll(subRoutes);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
    else{
        return new ResponseEntity<>(
                new ResourseNotFoundException("Route is not present"),HttpStatus.BAD_REQUEST);
    }
    }

    SubRoute mapToEntity(SubRouteDto dto){
        return mapper.map(dto,SubRoute.class);
    }
}
