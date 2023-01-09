package kodlama.io.devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.constants.Messages;
import kodlama.io.devs.business.mappers.ProgrammingLanguageMapper;
import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.CreateProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.business.responses.programmingLanguages.UpdateProgrammingLanguageResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
	private ProgrammingLanguageRepository programmingLanguageRepository;
	private ProgrammingLanguageMapper mapper;

	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository, ProgrammingLanguageMapper mapper) {
		this.programmingLanguageRepository = programmingLanguageRepository;
		this.mapper = mapper;
	}

	@Override
	public List<GetAllProgrammingLanguagesResponse> getAll() {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		return mapper.toGetAllProgrammingLanguagesResponse(programmingLanguages);
	}

	@Override
	public GetProgrammingLanguageByIdResponse getById(int id) {
		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.getReferenceById(id);
		return mapper.toGetProgrammingLanguageResponse(programmingLanguage);
	}

	@Override
	public CreateProgrammingLanguageResponse add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest){
		ProgrammingLanguage programmingLanguage = mapper.toProgrammingLanguage(createProgrammingLanguageRequest);

		if(isProgrammingLanguageExist(programmingLanguage)) throw new RuntimeException(Messages.PROGRAMMING_LANGUAGE_ALREADY_EXISTS);
		if(isProgrammingLanguageEmpty(programmingLanguage)) throw new RuntimeException(Messages.PROGRAMMING_LANGUAGE_NAME_IS_NOT_VALID);

		programmingLanguageRepository.save(programmingLanguage);
		return mapper.toCreateProgrammingLanguageResponse(programmingLanguage);
	}

	@Override
	public UpdateProgrammingLanguageResponse update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(updateProgrammingLanguageRequest.getId()).get();
        mapper.update(programmingLanguage, updateProgrammingLanguageRequest);

		programmingLanguageRepository.save(programmingLanguage);
		return mapper.toUpdateProgrammingLanguageResponse(programmingLanguage);
	}

	@Override
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
		programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
	}

	private boolean isProgrammingLanguageExist(ProgrammingLanguage programmingLanguage){
        for (ProgrammingLanguage languages : this.programmingLanguageRepository.findAll()){
            if (languages.getName().equals(programmingLanguage.getName())){
                return true;
            }
        }
        return false;
    }

    private boolean isProgrammingLanguageEmpty(ProgrammingLanguage programmingLanguage){
    	return programmingLanguage.getName().isEmpty() || programmingLanguage.getName().isBlank();
    }

}