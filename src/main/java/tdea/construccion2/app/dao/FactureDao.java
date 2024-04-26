package tdea.construccion2.app.dao;

import tdea.construccion2.app.dto.FactureDto;

public interface FactureDao {
	public void createFacture(FactureDto factureDto) throws Exception;
}
