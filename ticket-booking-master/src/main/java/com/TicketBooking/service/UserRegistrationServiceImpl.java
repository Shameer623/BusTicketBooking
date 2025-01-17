package com.TicketBooking.service;

import com.TicketBooking.Repository.RoleRepository;
import com.TicketBooking.Repository.UserRepository;
import com.TicketBooking.entity.Role;
import com.TicketBooking.entity.User;
import com.TicketBooking.payload.UserDto;
import com.TicketBooking.payload.UserRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.TicketBooking.service.EmailServiceImpl.*;



import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRegistrationServiceImpl {
    @Autowired
    private UserRepository regRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImpl emailService;


    public UserRegistrationDto getUser(long id) {
        User byId = regRepository.getReferenceById(id);
        return new UserRegistrationDto(id,
                byId.getUsername(), byId.getEmail());

    }

    public ResponseEntity<?> saveUser(UserDto dto, MultipartFile profilePicture) {
        if(regRepository.existsByEmail(dto.getEmail()) || regRepository.existsByUsername(dto.getUsername())){
            return new ResponseEntity<>(
                    "User exists with Email Or Username : "+dto.getEmail()+" "+ dto.getUsername(),HttpStatus.FOUND);
        }

        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        for (String role: dto.getRoles()){
            Role byName = roleRepository.getByName(role);
            roles.add(byName);
        }
        user.setRoles(roles);
        try {
            user.setProfilePicture(profilePicture.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User save = regRepository.save(user);
        emailService.sendOtp(dto.getEmail());
        UserRegistrationDto registrationDto = new UserRegistrationDto(
                save.getId(), save.getUsername(), save.getEmail());

        return new ResponseEntity<>(registrationDto+" Otp send successfully Please check your mail ", HttpStatus.OK);


    }

    public ResponseEntity<?> verifyOtp(String email, String otp) {
        String savedOtp = emailAndOtp.get(email);
        if (savedOtp!=null && savedOtp.equals(otp)){
            User user = (User) regRepository.findByUsernameOrEmail(email, email).get();
            emailAndOtp.remove(email);
            user.setEmailVerified(true);
            regRepository.save(user);
            return new ResponseEntity<>("Email verified Successfully !!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Otp is not matching please try again ",HttpStatus.BAD_REQUEST);

    }
}
