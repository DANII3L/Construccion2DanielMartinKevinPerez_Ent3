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

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.request.SearchOrder;
import tdea.construccion2.app.responseRequest.OrderResponse;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.OrderValidator;
import tdea.construccion2.app.validators.ValidatorsGenericImp;

@RestController
@RequestMapping("/Seller")
public class SellerController {
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private ValidatorsGenericImp validatorsGenericos;

	@PostMapping("/sellProducts")
	private ResponseEntity<String> sellProducts(@RequestBody FactureDto factureDto) throws Exception {
		validatorsGenericos.sellProducts(factureDto);
		
		String message = veterinaryService.createFacture(factureDto);
		
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/searchOrder")
	private ResponseEntity<OrderResponse> searchOrder(@RequestBody SearchOrder sOrder) throws Exception {
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

	public OrderValidator getOrderValidator() {
		return orderValidator;
	}

	public void setOrderValidator(OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
	}

	public ValidatorsGenericImp getValidatorsGenericos() {
		return validatorsGenericos;
	}

	public void setValidatorsGenericos(ValidatorsGenericImp validatorsGenericos) {
		this.validatorsGenericos = validatorsGenericos;
	}
}
