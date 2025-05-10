package com.testcasesdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="test_area")
public class TestArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "area" ,cascade = CascadeType.REMOVE)
    private Set<TestCase> testCases = new LinkedHashSet<>();


}
