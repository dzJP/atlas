package com.atlas.backend.service;

import com.atlas.backend.dto.RegisterRequest;
import com.atlas.backend.dto.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterRequest request);

}