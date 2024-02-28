package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Certification;
import com.backend.integration.Repo.CertificationRepository;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    public Optional<Certification> getCertificationById(Long certificateID) {
        return certificationRepository.findById(certificateID);
    }

    public Certification createCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    public void deleteCertification(Long certificateID) {
        certificationRepository.deleteById(certificateID);
    }
}