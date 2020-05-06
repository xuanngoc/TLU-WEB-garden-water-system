package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.SensorValue;

@Repository
public interface SensorValueRepository extends JpaRepository<SensorValue, Integer> {

    public List<SensorValue> findSensorValueBySensorId(Integer id);
}
