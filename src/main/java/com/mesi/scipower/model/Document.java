package com.mesi.scipower.model;

//import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
//@Entity
@NoArgsConstructor
public class Document {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private int year;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "doc_author", joinColumns = @JoinColumn(name = "doc_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id")
//    )
    private Set<Author> authors;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "doc_kw", joinColumns = @JoinColumn(name = "doc_id"),
//            inverseJoinColumns = @JoinColumn(name = "kw_id")
//    )
    private Set<KeyWord> keyWords;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "doc_ref", joinColumns = @JoinColumn(name = "doc_id"),
//            inverseJoinColumns = @JoinColumn(name = "ref_id")
//    )
    private Set<Reference> references;

}
