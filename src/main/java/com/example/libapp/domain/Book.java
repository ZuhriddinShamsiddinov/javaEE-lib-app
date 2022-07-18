package com.example.libapp.domain;

import com.example.libapp.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:21 AM 7/14/22 on Thursday in July
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String description;
    private String author;

    @Enumerated(EnumType.STRING)
    private Book.Genre genre;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Book.Status status = Status.CANCELED;
    @Enumerated(EnumType.STRING)
    private Language language;
    private int pageCount;
    private int downloadCount;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Uploads cover;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Uploads file;


    @AllArgsConstructor
    @Getter
    public enum Genre {
        CRIME("Crime"),
        HORROR("Horror"),
        CI_FI("Ci-fi"),
        DRAMA("Drama"),
        ROMANCE("Romance"),
        ROMANCE_DRAMA("Romance Drama"),
        FANTASY("Fantasy");
        private final String key;

        public static Book.Genre findByName(String genre) {
            return Arrays.stream(Book.Genre.values())
                    .filter(genre1 -> genre1.name().equals(genre))
                    .findFirst()
                    .orElse(Book.Genre.CRIME);
        }

        public String getKey() {
            return key;
        }
    }

    public enum Status {
        PINNED, CANCELED;
    }
}
