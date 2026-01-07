package com.github.kalilina.spring.dto;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
public class PageResponse<T> {
    List<T> content;
    Metadata metadata;

    public static <T> PageResponse<T> of(Page<T> page) {
        List<T> content = page.getContent();
        Metadata metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements());
        return new PageResponse<>(content, metadata);
    }

    @Value
    private static class Metadata {
        int page;
        int size;
        long totalElements;
    }
}
