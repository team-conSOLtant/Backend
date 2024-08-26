package com.consoltant.consoltant.domain.subject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE subject SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectNumber;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer credit;

    @Column(nullable = false)
    private Boolean isMajor;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

}
