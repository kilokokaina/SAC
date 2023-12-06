package com.mesi.scipower.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private int year;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doc_author", joinColumns = @JoinColumn(name = "doc_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @OneToMany(cascade = CascadeType.MERGE,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_id")
    private List<KeyWord> keyWords;

    @OneToMany(cascade = CascadeType.MERGE,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_id")
    private List<Reference> references;
}
