package tdea.construccion2.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdea.construccion2.app.models.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer>{

}
