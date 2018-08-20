package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.AuthorTO;
import com.capgemini.types.AuthorTO.AuthorTOBuilder;
import com.capgemini.types.BookTO;
import com.capgemini.types.BookTO.BookTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Test
	@Transactional
	public void testShouldFindBookById() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		BookTO savedBook = bookService.saveBook(panTadeuszBook);

		// when
		BookTO book = bookService.findBookById(savedBook.getId());

		// then
		assertNotNull(book);
		assertEquals(panTadeuszBook.getAuthors(), book.getAuthors());
		assertEquals(panTadeuszBook.getTitle(), book.getTitle());
	}

	@Test
	@Transactional
	public void testShouldFindBooksById() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		bookService.saveBook(panTadeuszBook);

		// when
		List<BookTO> books = bookService.findBooksByTitle(bookTitle);

		// then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		assertTrue(books.stream().anyMatch(b -> b.getTitle().equals(bookTitle)));
	}

	@Test
	@Transactional
	public void testShouldFindBooksByAuthor() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		BookTO savedBook = bookService.saveBook(panTadeuszBook);

		// when
		List<BookTO> books = bookService.findBooksByAuthor(savedBook.getAuthors().iterator().next().getId());

		// then
		assertNotNull(books);
		assertTrue(books.stream().anyMatch(b -> b.getTitle().equals(bookTitle)));
	}
}
