package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.business.responses.GetTechnologyByIdResponse;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import kodlama.io.devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
	private TechnologyRepository technologyRepository;
	private ProgrammingLanguageService programmingLanguageService;

	@Autowired
	public TechnologyManager(TechnologyRepository technologyRepository, ProgrammingLanguageService programmingLanguageService) {
		this.technologyRepository = technologyRepository;
		this.programmingLanguageService = programmingLanguageService;
	}

	@Override
	public List<GetAllTechnologiesResponse> getAll() {
		List<Technology> technologies = technologyRepository.findAll();
		List<GetAllTechnologiesResponse> getAllTechnologiesResponse = new ArrayList<GetAllTechnologiesResponse>();

		for (Technology technology : technologies) {
			GetAllTechnologiesResponse responseItem = new GetAllTechnologiesResponse();
			responseItem.setId(technology.getId());
			responseItem.setProgrammingLanguageName(technology.getProgrammingLanguage().getName());
			responseItem.setName(technology.getName());

			getAllTechnologiesResponse.add(responseItem);
		}

		return getAllTechnologiesResponse;
	}

	@Override
	public GetTechnologyByIdResponse getById(int id) {
		Technology technology = technologyRepository.getReferenceById(id);
		GetTechnologyByIdResponse getTechnologyByIdResponse = new GetTechnologyByIdResponse();

		getTechnologyByIdResponse.setId(technology.getId());
		getTechnologyByIdResponse.setProgrammingLanguageName(technology.getProgrammingLanguage().getName());
		getTechnologyByIdResponse.setName(technology.getName());

		return getTechnologyByIdResponse;
	}

	@Override
	public void add(CreateTechnologyRequest createTechnologyRequest) {
		Technology technology = new Technology();
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		GetProgrammingLanguageByIdResponse getProgrammingLanguageByIdResponse = programmingLanguageService.getById(createTechnologyRequest.getProgrammingLanguageId());

		programmingLanguage.setId(getProgrammingLanguageByIdResponse.getId());
		programmingLanguage.setName(getProgrammingLanguageByIdResponse.getName());

		technology.setName(createTechnologyRequest.getName());
		technology.setProgrammingLanguage(programmingLanguage);

		technologyRepository.save(technology);
	}

	@Override
	public void update(UpdateTechnologyRequest updateTechnologyRequest) {
		Technology technology = new Technology();
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		GetProgrammingLanguageByIdResponse getProgrammingLanguageByIdResponse = programmingLanguageService.getById(updateTechnologyRequest.getProgrammingLanguageId());

		programmingLanguage.setId(getProgrammingLanguageByIdResponse.getId());
		programmingLanguage.setName(getProgrammingLanguageByIdResponse.getName());

		technology.setId(updateTechnologyRequest.getId());
		technology.setName(updateTechnologyRequest.getName());
		technology.setProgrammingLanguage(programmingLanguage);

		technologyRepository.save(technology);
	}

	@Override
	public void delete(DeleteTechnologyRequest deleteTechnologyRequest) {
		technologyRepository.deleteById(deleteTechnologyRequest.getId());
	}
}
