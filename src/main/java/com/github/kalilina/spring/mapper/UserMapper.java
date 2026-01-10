package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserReadDto;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PersonalInfoMapper.class, CompanyMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    // create
    @Mapping(source = "companyId", target = "company", qualifiedByName = "mapCompany")
    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
    User toEntity(UserCreateEditDto userDto,
                  @Context CompanyRepository companyRepository);

    // update
    @Mapping(source = "companyId", target = "company", qualifiedByName = "mapCompany")
    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserCreateEditDto userDto, @MappingTarget User user,
                      @Context CompanyRepository companyRepository);

    // read
    UserReadDto toReadDto(User userEntity);

    @Named("mapCompany")
    default Company mapCompany(Integer companyId, @Context CompanyRepository companyRepository) {
        if (companyId == null) return null;

        return companyRepository.findById(companyId)
                .orElseThrow();
    }

    @Named("mapImage")
    default String mapImage(MultipartFile image) {
        return image == null || image.isEmpty()
                ? null
                : image.getOriginalFilename();
    }
}
