package com.backend.integration.Controller;

import com.backend.integration.Entity.Certification;
import com.backend.integration.Entity.FinalScore;
import com.backend.integration.Entity.QuizTaken;
import com.backend.integration.Service.CertificationService;
import com.backend.integration.Service.FinalScoreService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
public class CertificationController {
  @Autowired
  private CertificationService certificationService;

  @Autowired
  private FinalScoreService finalScoreService;

  @GetMapping("/certificate")
  public ResponseEntity<List<Certification>> getAllCertifications() {
    List<Certification> certifications = certificationService.getAllCertifications();
    return new ResponseEntity<>(certifications, HttpStatus.OK);
  }

  @GetMapping("/certificate/{id}")
  public ResponseEntity<Certification> getCertificationById(
    @PathVariable("id") Long certificateID
  ) {
    Optional<Certification> certification = certificationService.getCertificationById(
      certificateID
    );
    return certification
      .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/certificate/post")
  public ResponseEntity<String> createCertification(
    @RequestParam("serial_no") String serial_no,
    @RequestParam("file") MultipartFile certificate_file,
    @RequestParam("date_issued") Date date_issued,
    @RequestParam("time_issued") String time_issued,
    @RequestParam("criteria") String criteria,
    @RequestParam("fscoreId") Long fscoreId
  ) {

    // if (certificationService.existsByFinalScore(fscore_id)) {
    //     return ResponseEntity.badRequest().body("Certificate already exists for FinalScore: " + fscore_id);
    // }
    if (certificate_file.isEmpty()) {
      return ResponseEntity.badRequest().body("File is empty");
    }

    try {
      // Save the certificate file to a specific path
      byte[] bytes = certificate_file.getBytes();
      String originalFilename = certificate_file.getOriginalFilename();
      String filenameWithoutPrefix = originalFilename.startsWith("PDF")
        ? originalFilename.substring(3)
        : originalFilename;
      Path path = Paths.get(
        "C:\\Users\\allad\\Documents\\Project\\OfficeProject\\Main Code\\cmsproject_v.2\\public\\PDF\\" +
        filenameWithoutPrefix
      );
      Files.write(path, bytes);

      // Retrieve the associated QuizTaken entity
      Optional<FinalScore> finalScoreOptional = finalScoreService.getFinalScoreById(
        fscoreId
      );
      if (!finalScoreOptional.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body("FinalScore not found with ID: " + fscoreId);
      }

      FinalScore finalScore = finalScoreOptional.get();

      // Create a new Certification entity and set its properties
      Certification certification = new Certification();
      certification.setSerial_no(serial_no);
      certification.setDate_issued(date_issued);
      certification.setTime_issued(time_issued);
      certification.setCertificate_file(filenameWithoutPrefix);
      certification.setCriteria(criteria);
      certification.setFinalScore(finalScore); // Set QuizTaken in Certification
      certificationService.createCertification(certification);

      return ResponseEntity.ok("Certificate saved successfully");
    } catch (IOException e) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to save certificate: " + e.getMessage());
    }
  }

  @DeleteMapping("/certificate/remove/{id}")
  public ResponseEntity<Void> deleteCertification(
    @PathVariable("id") Long certificateID
  ) {
    certificationService.deleteCertification(certificateID);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/myCertification/{user_id}")
  public List<Certification> getCertificationByUserId(
    @PathVariable Long user_id
  ) {
    return certificationService.getCertificationByUserId(user_id);
  }
}
