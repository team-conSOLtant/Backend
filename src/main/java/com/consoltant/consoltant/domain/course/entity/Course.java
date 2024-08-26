package com.consoltant.consoltant.domain.course.entity;

import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE course SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    private String subjectName;

    private String grade;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isSelected = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void update(CourseRequestDto courseRequestDto){
        this.isSelected = courseRequestDto.getIsSelected();
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setSubject(Subject subject){
        this.subject = subject;
    }

    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

}
