package tdea.construccion2.app.service;

import java.util.List;

import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.HistoryClinicalDto;

@Component
public interface HistorialClinicalService {
	public String createHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
	public List<HistoryClinicalDto> consultHistoryClinical(HistoryClinicalDto historyClinicalDto) throws Exception;
}
