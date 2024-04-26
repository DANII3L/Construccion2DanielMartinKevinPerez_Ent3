package tdea.construccion2.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdea.construccion2.app.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	public Session findById(long Id); 
}