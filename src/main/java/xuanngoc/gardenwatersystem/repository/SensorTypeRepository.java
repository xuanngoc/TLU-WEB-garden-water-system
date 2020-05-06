package xuanngoc.gardenwatersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.SensorType;

import javax.persistence.Entity;

@Repository
public interface SensorTypeRepository  extends JpaRepository<SensorType, Integer> {

}
