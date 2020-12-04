package nl.brisksimple.costregistrationapi.repository;

import nl.brisksimple.costregistrationapi.model.CostRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRegistrationRepository extends JpaRepository<CostRegistration, Long> {
    List<CostRegistration> findByItemNameContaining(String itemName);
}
