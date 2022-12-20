package kodlama.io.devs.business.abstracts;

import java.util.List;

import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.CreateProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.business.responses.programmingLanguages.UpdateProgrammingLanguageResponse;

public interface ProgrammingLanguageService {
	List<GetAllProgrammingLanguagesResponse> getAll();
	GetProgrammingLanguageByIdResponse getById(int id);
	CreateProgrammingLanguageResponse add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest);
	UpdateProgrammingLanguageResponse update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);
	void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest);
}
