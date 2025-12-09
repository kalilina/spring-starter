package com.github.kalilina.service;

import com.github.kalilina.database.repository.CompanyRepository;
import com.github.kalilina.mapper.CompanyMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyService {

    private CompanyMapper companyMapper;
    private CompanyRepository companyRepository;
}
