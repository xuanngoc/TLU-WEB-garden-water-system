package xuanngoc.gardenwatersystem.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.SensorValue;

@Repository
public interface SensorValueRepository extends JpaRepository<SensorValue, Integer> {

    List<SensorValue> findSensorValueBySensorId(Integer id);


    @Query("select dateTime from SensorValue")
    List<Date> findDatesBySensorId(Integer id);

    /*@Query(value = "SELECT avg(value) FROM SensorValue WHERE ")
    public Double currentValue();*/


}
