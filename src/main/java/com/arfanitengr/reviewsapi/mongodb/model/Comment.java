package com.arfanitengr.reviewsapi.mongodb.model;

public class Comment {
    private String visitorName;
    private String commText;

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getCommText() {
        return commText;
    }

    public void setCommText(String commText) {
        this.commText = commText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "visitorName='" + visitorName + '\'' +
                ", commText='" + commText + '\'' +
                '}';
    }
}
