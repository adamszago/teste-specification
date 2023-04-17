package br.com.zago.especificacao.impl;

import java.util.List;

import br.com.zago.especificacao.Specification;
import br.com.zago.model.BookSample;

public class SampleSpecificationImpl {

	public static void main(String[] args) {
		 List<BookSample> books = List.of(
	                new BookSample("Inspired", "Marty Cagan", 85),
	                new BookSample("The Lost Symbol", "Dan Brown", 630),
	                new BookSample("Charlie", "Ives Saint", 25)
	        );

	        Specification<BookSample> pagesGreaterThan = new PagesGreaterThanSpecification(130);
	        Specification<BookSample> nameStartsWith = new NameStartsWithSpecification("T");

	        Specification<BookSample> spec1 = pagesGreaterThan.and(nameStartsWith);
	        Specification<BookSample> spec2 = pagesGreaterThan.or(nameStartsWith);
	        Specification<BookSample> spec3 = pagesGreaterThan.not();

	        List<BookSample> filtered1 = spec1.filter(books);
	        List<BookSample> filtered2 = spec2.filter(books);
	        List<BookSample> filtered3 = spec3.filter(books);

	        System.out.println(filtered1); // Output: [The Lost Symbol]
	        System.out.println(filtered2); // Output: [The Lost Symbol]
	        System.out.println(filtered3); // Output: [Inspired][Charlie]
	    }
	}

	class PagesGreaterThanSpecification implements Specification<BookSample> {
	    private final int pages;

	    PagesGreaterThanSpecification(int pages) {
	        this.pages = pages;
	    }

		@Override
		public boolean isSatisfiedBy(BookSample book) {
			return book.getPages() > pages;
		}
	}
	
	class NameStartsWithSpecification implements Specification<BookSample> {
		private String prefix;

		protected NameStartsWithSpecification(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public boolean isSatisfiedBy(BookSample book) {
			return book.getName().startsWith(prefix);
		}
		
		
	}

