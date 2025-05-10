package com.testcasesdb.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

import java.util.HashSet;
import java.util.LinkedHashSet;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_case")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "result", length = 100)
    private String result;

    @Column(name = "testname", length = 100)
    private String testname;

    @Column(name = "timeofexecution")
    private Instant timeofexecution;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private TestArea area;

    @Column(name = "assumption", length = 100)
    private String assumption;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="case_step",
            joinColumns=  @JoinColumn(name="case_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="step_id", referencedColumnName="id") )
    private Set<TestStep> testStep = new HashSet<TestStep>();
}
