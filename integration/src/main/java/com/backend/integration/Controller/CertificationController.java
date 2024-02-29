package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Entity.Certification;
import com.backend.integration.Service.CertificationService;

@RestController
@RequestMapping("/api/v1/auth")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @GetMapping("/certificate")
    public ResponseEntity<List<Certification>> getAllCertifications() {
        List<Certification> certifications = certificationService.getAllCertifications();
        return new ResponseEntity<>(certifications, HttpStatus.OK);
    }

    @GetMapping("/certificate/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable("id") Long certificateID) {
        Optional<Certification> certification = certificationService.getCertificationById(certificateID);
        return certification.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Certification> createCertification(@RequestBody Certification certification) {
        Certification createdCertification = certificationService.createCertification(certification);
        return new ResponseEntity<>(createdCertification, HttpStatus.CREATED);
    }

    @DeleteMapping("/certificate/remove/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable("id") Long certificateID) {
        certificationService.deleteCertification(certificateID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/myCertification/{user_id}")
  public List<Certification> getCertificationByUserId(
      @PathVariable Long user_id) {
    return certificationService.getCertificationByUserId(user_id);
  }
}
