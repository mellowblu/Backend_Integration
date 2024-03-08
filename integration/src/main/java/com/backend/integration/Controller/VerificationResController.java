package com.backend.integration.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Dto.Response.VerificationResponse;
import com.backend.integration.Service.VerificationResService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class VerificationResController {
    @Autowired
    private VerificationResService verificationResService;

    @GetMapping("/verifyCertificate/{serial_no}")
    public ResponseEntity<List<VerificationResponse>> verifyCertification(
            @PathVariable String serial_no) {
        List<VerificationResponse> verificationDetails = verificationResService
                .getCertificationDetailsBySerialNumber(serial_no);

        if (!verificationDetails.isEmpty()) {
            // Return details if found with HTTP status OK
            return new ResponseEntity<>(verificationDetails, HttpStatus.OK);
        } else {
            // Return NOT_FOUND if no details found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
