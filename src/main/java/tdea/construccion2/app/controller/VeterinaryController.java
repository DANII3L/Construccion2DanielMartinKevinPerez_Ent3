package tdea.construccion2.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.dto.PersonDto;
import tdea.construccion2.app.dto.PetDto;
import tdea.construccion2.app.request.SearchOrder;
import tdea.construccion2.app.responseRequest.OrderResponse;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.OrderValidator;
import tdea.construccion2.app.validators.ValidatorsGenericImp;

@RestController
@RequestMapping("/Veterinario")
public class VeterinaryController {
	@Autowired
	private ValidatorsGenericImp validatorsGenericos;
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private OrderValidator orderValidator;

	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody PersonDto personDto) throws Exception {
		validatorsGenericos.createPerson(personDto, "Veterinario");

		String message = veterinaryService.createUser(personDto, "Veterinario");

		return ResponseEntity.ok(message);
	}

	@PostMapping("/createPet")
	public ResponseEntity<String> createPet(@RequestBody PetDto petDto) throws Exception {
		validatorsGenericos.createPet(petDto);

		String message = veterinaryService.createPet(petDto);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/createHistoryClinical")
	public ResponseEntity<String> createHistoryClinical(@RequestBody HistoryClinicalDto historyClinicalDto) throws Exception {
		validatorsGenericos.createHistoryClinical(historyClinicalDto);

		String message = veterinaryService.createHistoryClinical(historyClinicalDto);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/searchHistoryClinical")
	public ResponseEntity<List<HistoryClinicalDto>> searchHistoryClinical(@RequestBody SearchOrder sOrder) throws Exception {
		int orderId = orderValidator.orderIdValidator(sOrder.getOrderId());

		List<HistoryClinicalDto> listHistoryDtoSearch = veterinaryService
				.consultHistoryClinical(new HistoryClinicalDto(orderId));

		if (!listHistoryDtoSearch.isEmpty())
			return ResponseEntity.ok(listHistoryDtoSearch);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("/searchOrder")
	public ResponseEntity<OrderResponse> searchOrder(@RequestBody SearchOrder sOrder) throws Exception {
		int orderId = orderValidator.orderIdValidator(sOrder.getOrderId());
		OrderDto orderSearch = veterinaryService.consultOrdern(new OrderDto(orderId));
		if (orderSearch != null) {
			OrderResponse response = new OrderResponse(orderSearch);
			List<HistoryClinicalDto> listHistorySearch = veterinaryService
					.consultHistoryClinical(new HistoryClinicalDto(orderSearch.getId()));
			for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
				response.setEstado(HistoryDtoSearch.getOrderCancellation());

			return ResponseEntity.ok(response);
		} else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("/cancelOrder")
	public ResponseEntity<String> cancelOrder(@RequestBody SearchOrder sOrder) throws Exception {
		int orderId = orderValidator.orderIdValidator(sOrder.getOrderId());

		veterinaryService.cancelOrdern(new OrderDto(orderId));
		return ResponseEntity.ok("Orden cancelada correctamente.");
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error: " + e.getMessage());
    }

	public OrderValidator getOrderValidator() {
		return orderValidator;
	}

	public void setOrderValidator(OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
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
