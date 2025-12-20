package com.github.kalilina.spring.dto;

import lombok.Builder;

@Builder
public record UserReadDto(Long id,
                          String username) {
}
