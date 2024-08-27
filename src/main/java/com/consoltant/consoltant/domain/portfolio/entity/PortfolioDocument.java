package com.consoltant.consoltant.domain.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Document(indexName = "portfolio")
public class PortfolioDocument {
    @Id @GeneratedValue
    @Field(type = Keyword)
    private String id;

    @Field(type = Long)
    private long userId;

    @Field(type = Text)
    private String userEmail;

    @Field(type = Long)
    private long portfolioId;

    @Field(type = Text)
    private String allContent;

    @Field(type = Boolean)
    private Boolean isEmployed;

    @Field(type = Double)
    private Double totalGpa;

    @Field(type = Double)
    private Double maxGpa;


    public void updatePortfolioDocument(String allContent, Boolean isEmployed, Double totalGpa, Double maxGpa) {
        this.allContent = allContent;
        this.isEmployed = isEmployed;
        this.totalGpa = totalGpa;
        this.maxGpa = maxGpa;
    }
}
