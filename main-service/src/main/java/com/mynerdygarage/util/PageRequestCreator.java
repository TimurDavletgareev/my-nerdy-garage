package com.mynerdygarage.util;

import com.mynerdygarage.error.exception.IncorrectRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageRequestCreator {

    public static PageRequest getPageRequest(int from, int size, Sort sort) {

        PageRequest pageRequest;

        if (size > 0 && from >= 0) {
            int page = from / size;
            pageRequest = PageRequest.of(page, size, sort);
        } else {
            throw new IncorrectRequestException("- Page size must be > 0, 'from' must be >= 0, " +
                    "'sort' must be proper class field");
        }

        return pageRequest;
    }
}
