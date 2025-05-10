package com.testcasesdb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_steps")
public class TestStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "description",length=10000)
    private String description;

    @Lob
    @Column(name = "screenshot_name",length=10000)
    private String screenshotName;

    @Lob
    @Column(name = "screenshot", length=100000000)
    @Basic(fetch = FetchType.LAZY)
    private byte[] screenshot;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="case_step",
            joinColumns=  @JoinColumn(name="step_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="case_id", referencedColumnName="id") )
    private Set<TestCase> testCase = new HashSet<TestCase>();
}
