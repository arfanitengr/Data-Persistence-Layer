package com.arfanitengr.reviewsapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String visitorName;
    private String commText;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    /*@Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", visitorName='" + visitorName + '\'' +
                ", commText='" + commText + '\'' +
                ", review=" + review +
                '}';
    }*/
}
