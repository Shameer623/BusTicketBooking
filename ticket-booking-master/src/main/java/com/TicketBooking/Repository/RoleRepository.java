package com.TicketBooking.Repository;

import com.TicketBooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getByName(String role);
}
