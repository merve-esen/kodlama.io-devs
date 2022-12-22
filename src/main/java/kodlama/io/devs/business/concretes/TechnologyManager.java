package kodlama.io.devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.mappers.TechnologyMapper;
import kodlama.io.devs.business.requests.technologies.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.technologies.CreateTechnologyResponse;
import kodlama.io.devs.business.responses.technologies.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.technologies.GetTechnologyByIdResponse;
import kodlama.io.devs.business.responses.technologies.UpdateTechnologyResponse;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
	private TechnologyRepository technologyRepository;
	private ProgrammingLanguageService programmingLanguageService;
	private TechnologyMapper mapper;


	public TechnologyManager(TechnologyRepository technologyRepository, ProgrammingLanguageService programmingLanguageService, TechnologyMapper mapper) {
		this.technologyRepository = technologyRepository;
		this.programmingLanguageService = programmingLanguageService;
		this.mapper = mapper;
	}

	@Override
	public List<GetAllTechnologiesResponse> getAll() {
		List<Technology> technologies = technologyRepository.findAll();
		return mapper.toGetAllTechnologiesResponse(technologies);
		/*List<GetAllTechnologiesResponse> getAllTechnologiesResponse = new ArrayList<GetAllTechnologiesResponse>();

		for (Technology technology : technologies) {
			GetAllTechnologiesResponse responseItem = new GetAllTechnologiesResponse();
			responseItem.setId(technology.getId());
			responseItem.setProgrammingLanguageName(technology.getProgrammingLanguage().getName());
			responseItem.setName(technology.getName());

			getAllTechnologiesResponse.add(responseItem);
		}

		return getAllTechnologiesResponse;*/
	}

	@Override
	public GetTechnologyByIdResponse getById(int id) {
		Technology technology = technologyRepository.getReferenceById(id);
		return mapper.toTechnology(technology);
	}

	@Override
	public CreateTechnologyResponse add(CreateTechnologyRequest createTechnologyRequest) {
		/*Technology technology = new Technology();
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		GetProgrammingLanguageByIdResponse getProgrammingLanguageByIdResponse = programmingLanguageService.getById(createTechnologyRequest.getProgrammingLanguageId());

		programmingLanguage.setId(getProgrammingLanguageByIdResponse.getId());
		programmingLanguage.setName(getProgrammingLanguageByIdResponse.getName());

		technology.setName(createTechnologyRequest.getName());
		technology.setProgrammingLanguage(programmingLanguage);*/

		Technology technology = mapper.toTechnology(createTechnologyRequest);
		technologyRepository.save(technology);
		return mapper.toCreateTechnologyResponse(technology);
	}

	@Override
	public UpdateTechnologyResponse update(UpdateTechnologyRequest updateTechnologyRequest) {
		/*Technology technology = new Technology();
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		GetProgrammingLanguageByIdResponse getProgrammingLanguageByIdResponse = programmingLanguageService.getById(updateTechnologyRequest.getProgrammingLanguageId());

		programmingLanguage.setId(getProgrammingLanguageByIdResponse.getId());
		programmingLanguage.setName(getProgrammingLanguageByIdResponse.getName());

		technology.setId(updateTechnologyRequest.getId());
		technology.setName(updateTechnologyRequest.getName());
		technology.setProgrammingLanguage(programmingLanguage);*/

		Technology technology = technologyRepository.findById(updateTechnologyRequest.getId()).get();
        mapper.update(technology, updateTechnologyRequest);
		technologyRepository.save(technology);
		return mapper.toUpdateTechnologyResponse(technology);
	}

	@Override
	public void delete(DeleteTechnologyRequest deleteTechnologyRequest) {
		technologyRepository.deleteById(deleteTechnologyRequest.getId());
	}
}
