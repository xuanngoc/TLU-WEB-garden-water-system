package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.SensorValue;
import xuanngoc.gardenwatersystem.repository.SensorValueRepository;

import javax.persistence.EntityNotFoundException;

import static xuanngoc.gardenwatersystem.service.PlantWaterService.plantWater;

@Service
public class SensorValueService {

    private SensorValueRepository sensorValueRepository;

    @Autowired
    public void setSensorValueRepository(SensorValueRepository sensorValueRepository) {
        this.sensorValueRepository = sensorValueRepository;
    }

    public List<SensorValue> findAllSensorValues() {
        return sensorValueRepository.findAll(Sort.by("id").ascending());
    }

    public List<SensorValue> findAllSensorValues(Integer sensorId) {
        return sensorValueRepository.findSensorValueBySensorId(sensorId);
    }

    public SensorValue findById(Integer id) {
        return sensorValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public SensorValue saveOrUpdate(SensorValue sensorValue) {
        plantWater(sensorValue.getSensor(), sensorValue.getValue());
        return sensorValueRepository.save(sensorValue);
    }

    public void delete(Integer id) {
        sensorValueRepository.deleteById(id);
    }

}
