package kodlama.io.devs.business.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import kodlama.io.devs.business.requests.technologies.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.technologies.CreateTechnologyResponse;
import kodlama.io.devs.business.responses.technologies.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.technologies.GetTechnologyByIdResponse;
import kodlama.io.devs.business.responses.technologies.UpdateTechnologyResponse;
import kodlama.io.devs.entities.concretes.Technology;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {

    @Mapping(source = "programmingLanguage.name", target = "programmingLanguageName")
    GetAllTechnologiesResponse toGetAllTechnologiesResponse(Technology technology);
    List<GetAllTechnologiesResponse> toGetAllTechnologiesResponse(List<Technology> technologies);
    @Mapping(source = "programmingLanguage.name", target = "programmingLanguageName")
    GetTechnologyByIdResponse toTechnology(Technology technology);
    @Mapping(source = "programmingLanguageId", target = "programmingLanguage.id")
    Technology toTechnology(CreateTechnologyRequest request);
    @Mapping(source = "programmingLanguage.id", target = "programmingLanguageId")
    CreateTechnologyResponse toCreateTechnologyResponse(Technology technology);
    @Mapping(source = "programmingLanguageId", target = "programmingLanguage.id")
    void update(@MappingTarget Technology technology, UpdateTechnologyRequest request);
    @Mapping(source = "programmingLanguage.id", target = "programmingLanguageId")
    UpdateTechnologyResponse toUpdateTechnologyResponse(Technology technology);
}
