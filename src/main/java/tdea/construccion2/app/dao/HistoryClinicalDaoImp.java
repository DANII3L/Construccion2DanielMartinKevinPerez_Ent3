package tdea.construccion2.app.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.HistoryClinicalDto;
import tdea.construccion2.app.models.HistoryClinical;
import tdea.construccion2.app.repository.HistoryClinicalRepository;

@Service
public class HistoryClinicalDaoImp implements HistoryClinicalDao {
	@Autowired
	private HistoryClinicalRepository historyClinicalRepository;
	
	@Override
	public void createHistory(HistoryClinicalDto historyClinicalDto) throws Exception {
		historyClinicalRepository.save(new HistoryClinical(historyClinicalDto));
	}

	@Override
	public void cancelOrder(int OrderId) throws Exception {
		List<HistoryClinicalDto> listH = this.findHistoryByOrden(new HistoryClinicalDto(OrderId));
		for(HistoryClinicalDto history : listH) {
			history.setOrderCancellation(true);
			historyClinicalRepository.save(new HistoryClinical(history));
		}
	}

	@Override
	public List<HistoryClinicalDto> findHistoryByOrden(HistoryClinicalDto historyClinicalDto) throws Exception {
		List<HistoryClinical> hList = historyClinicalRepository.findByOrderID(historyClinicalDto.getOrderID());
		
		return hList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	private HistoryClinicalDto toDTO(HistoryClinical historyC) {
        return new HistoryClinicalDto(historyC);
    }

	public HistoryClinicalRepository getHistoryClinicalRepository() {
		return historyClinicalRepository;
	}

	public void setHistoryClinicalRepository(HistoryClinicalRepository historyClinicalRepository) {
		this.historyClinicalRepository = historyClinicalRepository;
	}
}
