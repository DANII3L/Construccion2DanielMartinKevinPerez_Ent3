package tdea.construccion2.app.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.service.VeterinaryService;
import tdea.construccion2.app.validators.FactureValidator;
import tdea.construccion2.app.validators.OrderValidator;
import tdea.construccion2.app.validators.PersonValidator;
import tdea.construccion2.app.validators.PetValidator;
@Component
public class SellerController {
	private static Scanner reader = new Scanner(System.in);
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private PersonValidator personValidator;
	@Autowired
	private PetValidator petValidator;
	@Autowired
	private FactureValidator factureValidator;
	@Autowired
	private OrderValidator orderValidator;
	private static final String MENU = "ingrese\n1.Venta de medicamentos\n2.Para busqueda de orden\n3.Para cerrar Sesion";

	public void session() {
		boolean runApp = true;
		while (runApp) {
			try {
				System.out.println(MENU);
				String option = reader.nextLine();
				runApp = menu(option);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void sellProducts() throws Exception {
		System.out.println("ingrese el id de la mascota");
		int petId = petValidator.petIdValidator(reader.nextLine());
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		System.out.println("ingrese nombre de/los medicamento/s");
		String nameMedicine = reader.nextLine();
		factureValidator.medicineNameValidator(nameMedicine);
		System.out.println("ingrese el valor de/los medicamento/s");
		double costMedicine = factureValidator.costValidator(reader.nextLine());
		System.out.println("ingrese la cantidad de/los medicamento/s");
		int amountMedicine = factureValidator.amountValidator(reader.nextLine());

		veterinaryService.createFacture(new FactureDto(petId, orderId, nameMedicine, costMedicine, amountMedicine));
	}

	public void searchOrder() throws Exception {
		System.out.println("ingrese el id de la orden");
		int orderId = orderValidator.orderIdValidator(reader.nextLine());
		OrderDto orderSearch = veterinaryService.consultOrdern(new OrderDto(orderId));
		if (orderSearch != null) {
			System.out.println("Orden encontrada:");
			System.out.println("Mascota: " + orderSearch.getPetId());
			System.out.println("Cédula de dueño: " + orderSearch.getOwnerID());
			System.out.println("Veterinario: " + orderSearch.getVetId());
			System.out.println("Nombre de medicamento/s: " + orderSearch.getMedicineName());
			System.out.println("Fecha de registro: " + orderSearch.getDateRegister());
			List<HistoryClinicalDto> listHistorySearch = veterinaryService
					.consultHistoryClinical(new HistoryClinicalDto(orderSearch.getId()));
			for (HistoryClinicalDto HistoryDtoSearch : listHistorySearch)
				if (HistoryDtoSearch.getOrderCancellation())
					System.out.println("El estado de la orden es cancelado.");
			System.out.println("-------------------------------------");
		} else
			System.out.println("No se ha encontrado una orden");
	}

	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1":
			sellProducts();
			return true;
		case "2":
			searchOrder();
			return true;
		case "3":
			return false;
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}

	public VeterinaryService getVeterinaryService() {
		return veterinaryService;
	}

	public void setVeterinaryService(VeterinaryService veterinaryService) {
		this.veterinaryService = veterinaryService;
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	public PetValidator getPetValidator() {
		return petValidator;
	}

	public void setPetValidator(PetValidator petValidator) {
		this.petValidator = petValidator;
	}

	public FactureValidator getFactureValidator() {
		return factureValidator;
	}

	public void setFactureValidator(FactureValidator factureValidator) {
		this.factureValidator = factureValidator;
	}

	public OrderValidator getOrderValidator() {
		return orderValidator;
	}

	public void setOrderValidator(OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
	}
}
