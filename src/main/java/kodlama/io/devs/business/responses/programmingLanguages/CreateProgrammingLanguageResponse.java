package kodlama.io.devs.business.responses.programmingLanguages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProgrammingLanguageResponse {
	private int id;
	private String name;
}
