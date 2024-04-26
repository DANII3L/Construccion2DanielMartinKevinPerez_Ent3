package tdea.construccion2.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdea.construccion2.app.models.HistoryClinical;

@Repository
public interface HistoryClinicalRepository extends JpaRepository<HistoryClinical, Date>{
	public List<HistoryClinical> findByOrderID(int OrderId);
}
