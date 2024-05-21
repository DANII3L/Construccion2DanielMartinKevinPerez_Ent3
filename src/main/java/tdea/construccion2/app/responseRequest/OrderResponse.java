package tdea.construccion2.app.responseRequest;


import tdea.construccion2.app.dto.OrderDto;

public class OrderResponse extends OrderDto {

	private Boolean estado;

	public OrderResponse(OrderDto orderDto) {
        super(orderDto.getId(), orderDto.getPetId(), orderDto.getOwnerID(), orderDto.getVetId(), orderDto.getMedicineName(), orderDto.getDateRegister());
    }
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
