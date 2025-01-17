package com.TicketBooking.Repository;

import com.TicketBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<Object> findByUsernameOrEmail(String username, String username1);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
