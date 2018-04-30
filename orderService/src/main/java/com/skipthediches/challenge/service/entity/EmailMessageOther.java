package com.skipthediches.challenge.service.entity;

import java.io.Serializable;

public class EmailMessageOther implements Serializable {

    private String subject;

    private String to;

    private String body;

    public EmailMessageOther() {}

    public EmailMessageOther(String subject, String body, String to) {
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
