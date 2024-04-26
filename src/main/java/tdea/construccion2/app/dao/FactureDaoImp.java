package tdea.construccion2.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.FactureDto;
import tdea.construccion2.app.models.Facture;
import tdea.construccion2.app.repository.FactureRepository;

@Service
public class FactureDaoImp implements FactureDao {
	@Autowired
	private FactureRepository factureRepository;
	
	@Override
	public void createFacture(FactureDto factureDto) throws Exception {
		factureRepository.save(new Facture(factureDto));
	}

	public FactureRepository getFactureRepository() {
		return factureRepository;
	}

	public void setFactureRepository(FactureRepository factureRepository) {
		this.factureRepository = factureRepository;
	}
}
