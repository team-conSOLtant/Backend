package com.consoltant.consoltant.domain.project.entity;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
@SQLDelete(sql = "UPDATE project SET is_deleted = true WHERE id = ?")
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

    @Column(length = 3000)
    private String contents;

    @Column(length = 1000)
    private String projectUrl;

    @OneToMany(mappedBy = "project")
    @Builder.Default
    private final List<ProjectUser> projectUsers = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setPortfolio(Portfolio portfolio){
        this.portfolio = portfolio;
    }

    public void update(ProjectRequestDto projectRequestDto){
        this.title = projectRequestDto.getTitle();
        this.language = projectRequestDto.getLanguage();
        this.description = projectRequestDto.getDescription();
        this.projectUrl = projectRequestDto.getProjectUrl();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
    }
}
