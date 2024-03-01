package com.backend.integration.Entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private String verificationCode;
    private String generatedCode;
    private LocalDateTime expirationTime; 

    // Getter and Setter for content
    public String getContent() {
        return msgBody;
    }

    public void setContent(String content) {
        this.msgBody = content;
    }

    // Setter for expirationTime
    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    // Getter for expirationTime
    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
}
