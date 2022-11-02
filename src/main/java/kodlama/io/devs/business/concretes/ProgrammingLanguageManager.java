package kodlama.io.devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
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
	public void add(ProgrammingLanguage programmingLanguage){
		try {
			if(isProgrammingLanguageExist(programmingLanguage)) throw new Exception("Bu programlama dili zaten var");
			if(isProgrammingLanguageEmpty(programmingLanguage)) throw new Exception("Programlama dili boş bırakılamaz");

			programmingLanguageRepository.add(programmingLanguage);
		} catch (Exception exception) {
			// TODO: handle exception
		}
	}

	@Override
	public void update(ProgrammingLanguage programmingLanguage) {
		programmingLanguageRepository.update(programmingLanguage);
	}

	@Override
	public void delete(int id) {
		programmingLanguageRepository.delete(id);
	}

	@Override
	public List<ProgrammingLanguage> getAll() {
		return programmingLanguageRepository.getAll();
	}

	@Override
	public ProgrammingLanguage getById(int id) {
		return programmingLanguageRepository.getById(id);
	}

	private boolean isProgrammingLanguageExist(ProgrammingLanguage programmingLanguage){
        for (ProgrammingLanguage languages : this.programmingLanguageRepository.getAll()){
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