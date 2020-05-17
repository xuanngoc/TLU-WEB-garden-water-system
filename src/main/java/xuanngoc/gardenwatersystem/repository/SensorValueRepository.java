package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.SensorValue;
import xuanngoc.gardenwatersystem.model.SensorValueCustom;

@Repository
public interface SensorValueRepository extends JpaRepository<SensorValue, Integer> {

    List<SensorValue> findSensorValueBySensorId(Integer id);


    @Query(value = "select avg(VALUE), EXTRACT(year from date_time) AS year, EXTRACT(month from date_time) AS month, EXTRACT(day FROM date_time) AS day, EXTRACT(hour from date_time) AS hour, EXTRACT(minute FROM date_time) AS minute FROM (SELECT value, date_time FROM (SELECT * FROM sensor WHERE garden_id = ?2  AND sensor_type_id = ?1) AS s INNER JOIN sensor_value AS sv ON s.id = sv.sensor_id) AS sv GROUP BY year, month, day, hour, minute ORDER BY year ASC, month ASC, day ASC, hour ASC, minute ASC",
        nativeQuery = true
    )

    List<SensorValueCustom> findAvgSensorValueByGardenId(Integer sensorTypeId, Integer gardenId);

    @Query(value = "SELECT AVG(value) FROM (SELECT value, date_time FROM (SELECT * FROM sensor WHERE sensor_type_id = ?1 AND garden_id = ?2) AS s INNER JOIN sensor_value AS sv ON s.id = sv.sensor_id) AS sv GROUP BY date_time ORDER BY date_time DESC LIMIT 1",
        nativeQuery = true
    )
    Double findAvgTop1DateTimeValueByGardenId(Integer sensorTypeId, Integer gardenId);

}
