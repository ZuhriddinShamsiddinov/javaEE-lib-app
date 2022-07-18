package com.example.libapp.dto;

import lombok.*;

import javax.servlet.http.Part;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:31 PM 7/13/22 on Wednesday in July
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDTO {
    private String name;
    private String description;
    private String author;
    private String genre;
    private String language;
    private int pageCount;
    private Part cover;
    private Part file;
}
