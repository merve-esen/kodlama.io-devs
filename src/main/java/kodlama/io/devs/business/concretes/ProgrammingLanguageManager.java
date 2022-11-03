package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {
	private ProgrammingLanguageRepository programmingLanguageRepository;

	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository) {
		this.programmingLanguageRepository = programmingLanguageRepository;
	}

	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest){
		try {
			ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
			programmingLanguage.setName(createProgrammingLanguageRequest.getName());

			if(isProgrammingLanguageExist(programmingLanguage)) throw new Exception("Bu programlama dili zaten var");
			if(isProgrammingLanguageEmpty(programmingLanguage)) throw new Exception("Programlama dili boş bırakılamaz");

			programmingLanguageRepository.save(programmingLanguage);
		} catch (Exception exception) {
			// TODO: handle exception
		}
	}

	@Override
	public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setId(updateProgrammingLanguageRequest.getId());
		programmingLanguage.setName(updateProgrammingLanguageRequest.getName());

		programmingLanguageRepository.save(programmingLanguage);
	}

	@Override
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
		programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
	}

	@Override
	public List<GetAllProgrammingLanguagesResponse> getAll() {
		List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
		List<GetAllProgrammingLanguagesResponse> getAllProgrammingLanguagesResponse = new ArrayList<GetAllProgrammingLanguagesResponse>();

		for (ProgrammingLanguage programmingLanguage : programmingLanguages) {
			GetAllProgrammingLanguagesResponse responseItem = new GetAllProgrammingLanguagesResponse();
			responseItem.setId(programmingLanguage.getId());
			responseItem.setName(programmingLanguage.getName());

			getAllProgrammingLanguagesResponse.add(responseItem);
		}

		return getAllProgrammingLanguagesResponse;
	}

	@Override
	public GetProgrammingLanguageByIdResponse getById(int id) {
		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.getReferenceById(id);
		GetProgrammingLanguageByIdResponse getProgrammingLanguageByIdResponse = new GetProgrammingLanguageByIdResponse();

		getProgrammingLanguageByIdResponse.setId(programmingLanguage.getId());
		getProgrammingLanguageByIdResponse.setName(programmingLanguage.getName());

		return getProgrammingLanguageByIdResponse;
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