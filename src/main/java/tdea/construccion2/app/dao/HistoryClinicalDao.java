package tdea.construccion2.app.dao;

import java.util.List;

import tdea.construccion2.app.dto.HistoryClinicalDto;

public interface HistoryClinicalDao {
	public void createHistory(HistoryClinicalDto historyClinicalDto) throws Exception;
	public void cancelOrder(int OrderId) throws Exception;
	public List<HistoryClinicalDto> findHistoryByOrden(HistoryClinicalDto historyClinicalDto) throws Exception;
}
