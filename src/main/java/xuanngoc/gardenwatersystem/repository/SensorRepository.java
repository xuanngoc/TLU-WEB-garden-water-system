package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorProperties;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public List<Sensor> findBySensorTypeId(Integer id);

    @Query(value = "SELECT id, name, state, status, garden_id, sensor_type_id FROM sensor",
    nativeQuery = true)
    public List<SensorProperties> getSensors();
}
