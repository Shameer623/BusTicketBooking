package com.TicketBooking.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_routes")
public class SubRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subRouteName;
    private String fromDate;
    private String toDate;
    @Column(name = "from_destination")
    private String fromLocation;
    @Column(name = "to_destination")
    private String toLocation;
    private String arrivalTime;
    private String departureTime;
    private long totalDistance;
    private long routeId;
}

