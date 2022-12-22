package kodlama.io.devs.business.abstracts;

import java.util.List;

import kodlama.io.devs.business.requests.technologies.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.technologies.CreateTechnologyResponse;
import kodlama.io.devs.business.responses.technologies.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.technologies.GetTechnologyByIdResponse;
import kodlama.io.devs.business.responses.technologies.UpdateTechnologyResponse;

public interface TechnologyService {
	List<GetAllTechnologiesResponse> getAll();
	GetTechnologyByIdResponse getById(int id);
	CreateTechnologyResponse add(CreateTechnologyRequest createTechnologyRequest);
	UpdateTechnologyResponse update(UpdateTechnologyRequest updateTechnologyRequest);
	void delete(DeleteTechnologyRequest deleteTechnologyRequest);
}
