package com.consoltant.consoltant.domain.project.entity;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE project SET is_deleted = true WHERE project_id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 100)
    private String language;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String projectUrl;

    @Column(nullable = false)
    private Boolean isDeleted;
}
