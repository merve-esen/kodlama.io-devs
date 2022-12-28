package kodlama.io.devs.business.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.CreateProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.business.responses.programmingLanguages.UpdateProgrammingLanguageResponse;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

@Mapper(componentModel = "spring")
public interface ProgrammingLanguageMapper {

	GetAllProgrammingLanguagesResponse toGetAllProgrammingLanguagesResponse(ProgrammingLanguage programmingLanguage);

    List<GetAllProgrammingLanguagesResponse> toGetAllProgrammingLanguagesResponse(List<ProgrammingLanguage> programmingLanguages);

    GetProgrammingLanguageByIdResponse toGetProgrammingLanguageResponse(ProgrammingLanguage programmingLanguage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technologies", ignore = true)
    ProgrammingLanguage toProgrammingLanguage(CreateProgrammingLanguageRequest request);

    CreateProgrammingLanguageResponse toCreateProgrammingLanguageResponse(ProgrammingLanguage programmingLanguage);

    @Mapping(target = "technologies", ignore = true)
    void update(@MappingTarget ProgrammingLanguage programmingLanguage, UpdateProgrammingLanguageRequest request);

    UpdateProgrammingLanguageResponse toUpdateProgrammingLanguageResponse(ProgrammingLanguage programmingLanguage);
}