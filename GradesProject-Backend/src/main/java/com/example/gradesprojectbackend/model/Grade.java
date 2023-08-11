package com.example.gradesprojectbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grade {


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrade;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_Grade_Student"))
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_Grade_Course"))
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false, foreignKey = @ForeignKey(name = "FK_Grade_Classroom"))
    private Classroom classroom;

    @Column(nullable = false, length = 10)
    private String bimester;

    @Column(nullable = false)
    @Check(constraints = "exam1 BETWEEN 0 AND 20", name = "Exam1Range")
    private Double exam1;

    @Column(nullable = false)
    @Check(constraints = "exam2 BETWEEN 0 AND 20", name = "Exam2Range")
    private Double exam2;

    @Column(nullable = false)
    @Check(constraints = "homeworks BETWEEN 0 AND 20", name = "HomeworksRange")
    private Double homeworks;

    @Column(nullable = false)
    @Check(constraints = "final_exam BETWEEN 0 AND 20", name = "FinalExamRange")
    private Double finalExam;

    @Column(nullable = false, length = 40)
    private String status;

    @Column(nullable = false)
    @Check(constraints = "average BETWEEN 0 AND 20", name = "AverageRange")
    private Double average;

    @Column(nullable = false)
    private boolean enabled;
}
