package kodlama.io.devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private TechnologyMapper mapper;

	@Autowired
	public TechnologyManager(TechnologyRepository technologyRepository, TechnologyMapper mapper) {
		this.technologyRepository = technologyRepository;
		this.mapper = mapper;
	}

	@Override
	public List<GetAllTechnologiesResponse> getAll() {
		List<Technology> technologies = technologyRepository.findAll();
		return mapper.toGetAllTechnologiesResponse(technologies);
	}

	@Override
	public GetTechnologyByIdResponse getById(int id) {
		Technology technology = technologyRepository.getReferenceById(id);
		return mapper.toTechnology(technology);
	}

	@Override
	public CreateTechnologyResponse add(CreateTechnologyRequest createTechnologyRequest) {
		Technology technology = mapper.toTechnology(createTechnologyRequest);
		technologyRepository.save(technology);
		return mapper.toCreateTechnologyResponse(technology);
	}

	@Override
	public UpdateTechnologyResponse update(UpdateTechnologyRequest updateTechnologyRequest) {
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
