package com.skipthediches.challenge.service.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EmailMessage implements Serializable {

    private String subject;

    @NotNull
    @Email
    private String to;

    private String body;

    public EmailMessage() {}

    public EmailMessage(String to, String subject, String body) {
        this.subject = subject;
        this.to = to;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
