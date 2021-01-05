package pl.janeck.tempsaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.janeck.tempsaver.model.Measurement;

/**
 * ... comment class...
 *
 * @author JKN janeck@protonmail.com
 * @since 02 January 2021 @ 19:35
 */

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
