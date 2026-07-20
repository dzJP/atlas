package com.atlas.backend.service;

import com.atlas.backend.dto.RegisterRequest;
import com.atlas.backend.dto.RegisterResponse;
import com.atlas.backend.entity.Business;
import com.atlas.backend.entity.Role;
import com.atlas.backend.entity.User;
import com.atlas.backend.exception.EmailAlreadyExistsException;
import com.atlas.backend.repository.BusinessRepository;
import com.atlas.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            BusinessRepository businessRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        Business business = createBusiness(request);
        businessRepository.save(business);

        User user = createUser(request, business);
        userRepository.save(user);

        return new RegisterResponse(
                business.getId(),
                user.getId()
        );
    }

    private Business createBusiness(RegisterRequest request) {

        Business business = new Business();

        business.setName(request.getBusinessName());
        business.setIndustry("Unknown");
        business.setCountry("Sweden");
        business.setTimezone("Europe/Stockholm");
        business.setActive(true);

        return business;
    }

    private User createUser(RegisterRequest request, Business business) {

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user.setRole(Role.ADMIN);
        user.setBusiness(business);
        user.setEnabled(true);

        return user;
    }
}