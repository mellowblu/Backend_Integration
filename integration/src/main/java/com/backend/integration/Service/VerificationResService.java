package com.backend.integration.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Dto.Response.VerificationResponse;
import com.backend.integration.Repo.VerificationResRepository;

@Service
public class VerificationResService {
    @Autowired
    private VerificationResRepository verificationResRepository;
    
    public List<VerificationResponse> getCertificationDetailsBySerialNumber(
        String serial_no
    ) {
        return verificationResRepository.findBySerialNumberWithDetails(serial_no);
    }
}
