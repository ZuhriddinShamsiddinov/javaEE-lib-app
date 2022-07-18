package com.example.libapp.service;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.dao.BookDAO;
import com.example.libapp.domain.Book;
import com.example.libapp.domain.Uploads;
import com.example.libapp.dto.BookCreateDTO;
import com.example.libapp.enums.Language;
import com.example.libapp.exceptions.InvalidInputException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:39 PM 7/13/22 on Wednesday in July
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookService {

    private static BookService instance;
    private final FileUploadsService fileUploadsService = ApplicationContextHolder.getBean(FileUploadsService.class);
    private final BookDAO bookDAO = ApplicationContextHolder.getBean(BookDAO.class);

    public void save(BookCreateDTO dto) throws InvalidInputException {
        Book byBookName = bookDAO.findByBookName(dto.getName());
        if (Objects.nonNull(byBookName)) {
            throw new InvalidInputException("Book name already taken by '%s'".formatted(dto.getName()));
        }
        Uploads file = fileUploadsService.upload(dto.getFile());
        Uploads cover = fileUploadsService.upload(dto.getCover());

        Book book = Book
                .builder()
                .author(dto.getAuthor())
                .name(dto.getName())
                .description(dto.getDescription())
                .genre(Book.Genre.findByName(dto.getGenre()))
                .language(Language.findByName(dto.getLanguage()))
                .cover(cover)
                .file(file)
                .build();
        bookDAO.save(book);
    }

    public List<Book> getAllForUsers() {
        return bookDAO.findAllStatusPinned();
    }

    public List<Book> getAllForAdmin() {
        return bookDAO.findAll();
    }

    public void deleteBook(String name) {
        Book byBookName = bookDAO.findByBookName(name);
        byBookName.setStatus(Book.Status.CANCELED);
        bookDAO.save(byBookName);
    }

    public void pinBook(String name) {
        Book byBookName = bookDAO.findByBookName(name);
        byBookName.setStatus(Book.Status.PINNED);
        bookDAO.save(byBookName);
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }
}
