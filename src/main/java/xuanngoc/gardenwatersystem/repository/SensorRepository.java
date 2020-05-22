package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorProperties;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public List<Sensor> findBySensorTypeId(Integer id);

    public List<SensorProperties> getSensors();
}
