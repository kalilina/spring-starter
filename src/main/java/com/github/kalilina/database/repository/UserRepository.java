package com.github.kalilina.database.repository;

import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRepository {

    private String username;
    private int poolSize;
    private List<Object> args;
    private Map<String, Object> properties;
}
