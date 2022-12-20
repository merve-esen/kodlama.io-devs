package kodlama.io.devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.programmingLanguages.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.programmingLanguages.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.programmingLanguages.CreateProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.programmingLanguages.GetProgrammingLanguageByIdResponse;
import kodlama.io.devs.business.responses.programmingLanguages.UpdateProgrammingLanguageResponse;

@RestController
@RequestMapping("/api/programmingLanguages")
public class ProgrammingLanguagesController {

    private ProgrammingLanguageService programmingLanguageService;

    @Autowired
    public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
        this.programmingLanguageService = programmingLanguageService;
    }

    @GetMapping("/getall")
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        return programmingLanguageService.getAll();
    }

    @GetMapping("/{id}")
	public GetProgrammingLanguageByIdResponse getById(@PathVariable int id) {
		return programmingLanguageService.getById(id);
	}

    @PostMapping("/add")
    public CreateProgrammingLanguageResponse add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
        return programmingLanguageService.add(createProgrammingLanguageRequest);
    }

    @PostMapping("/update")
    public UpdateProgrammingLanguageResponse update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
        return programmingLanguageService.update(updateProgrammingLanguageRequest);
    }

    @DeleteMapping("/delete")
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
		programmingLanguageService.delete(deleteProgrammingLanguageRequest);
	}
}
