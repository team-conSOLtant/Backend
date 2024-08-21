package com.consoltant.consoltant.domain.portfolio.entity;

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
    private long portfolioId;

    @Field(type = Text)
    private String allContent;
}
