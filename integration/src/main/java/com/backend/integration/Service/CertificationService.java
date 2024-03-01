package com.backend.integration.Service;

import com.backend.integration.Entity.Certification;
import com.backend.integration.Entity.FinalScore;
import com.backend.integration.Repo.CertificationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<Certification> getCertificationByUserId(Long user_id) {
    return certificationRepository.findByUserId(user_id);
  }

  //   public boolean existsByFinalScoreId(Long fscore_id) {
  //     return certificationRepository.existsByFinalScore_FscoreId(fscore_id);
  //   }

//   public boolean existsByFinalScore(long fscore_id) {
//     return certificationRepository.existsByFinalScoreFscoreId(fscore_id);
//   }
}
