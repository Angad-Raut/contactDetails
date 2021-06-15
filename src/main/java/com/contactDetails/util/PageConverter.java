package com.contactDetails.util;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * The Class PageConverter. Useful for conversion of one page to other page.
 *
 * @author Angad Raut
 * @version 1.0
 * @since 14 June, 2021
 */
@Component
public class PageConverter {

    /**
     * Map. Provide functionality to convert one response page into other response page.
     *
     * @param <Destination> the generic type
     * @param <Source> the generic type
     * @param page the page
     * @param list the list
     * @return the page
     */
    public static <Destination, Source> Page<Destination> map(Page<Source> page, List<Destination> list) {

        return new Page<Destination>() {

            @Override
            public int getNumber() {
                return page.getNumber();
            }

            @Override
            public int getSize() {
                return page.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return page.getNumberOfElements();
            }

            @Override
            public List<Destination> getContent() {
                return list;
            }

            @Override
            public boolean hasContent() {
                return page.hasContent();
            }

            @Override
            public Sort getSort() {
                return page.getSort();
            }

            @Override
            public boolean isFirst() {
                return page.isFirst();
            }

            @Override
            public boolean isLast() {
                return page.isLast();
            }

            @Override
            public boolean hasNext() {
                return page.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return page.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return page.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return page.previousPageable();
            }

            @Override
            public Iterator<Destination> iterator() {
                return list.iterator();
            }

            @Override
            public int getTotalPages() {
                return page.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return page.getTotalElements();
            }

			@Override
			public <U> Page<U> map(Function<? super Destination, ? extends U> arg0) {
				return null;
			}
			
        };
    }
}
