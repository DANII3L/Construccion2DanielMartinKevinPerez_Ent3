package tdea.construccion2.app.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.ValidatorsGenericImp;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private ValidatorsGenericImp validatorsGenericos;
	
	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody PersonDto personDto) throws Exception {
		validatorsGenericos.createPerson(personDto, "Administrador");
		
		String message = veterinaryService.createUser(personDto, "Administrador");
		
		return ResponseEntity.ok(message);
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error: " + e.getMessage());
    }

	public VeterinaryService getVeterinaryService() {
		return veterinaryService;
	}

	public void setVeterinaryService(VeterinaryService veterinaryService) {
		this.veterinaryService = veterinaryService;
	}

	public ValidatorsGenericImp getValidatorsGenericos() {
		return validatorsGenericos;
	}

	public void setValidatorsGenericos(ValidatorsGenericImp validatorsGenericos) {
		this.validatorsGenericos = validatorsGenericos;
	}
}
