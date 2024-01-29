package com.mesi.scipower.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Long SCOPUS_ID;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_affilation", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "aff_id")
    )
    private Set<Affiliation> affiliationSet;

}
