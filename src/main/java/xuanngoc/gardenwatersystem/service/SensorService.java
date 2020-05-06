package xuanngoc.gardenwatersystem.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.repository.SensorRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorService {

    private SensorRepository sensorRepository;

    private static void accept(Sensor sensor) {
        boolean statusDevice = PlantWaterService.checkStatusDevice(sensor.getDevice());
        sensor.setState(statusDevice);
    }

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    public List<Sensor> findAllSensors() {
        List<Sensor> sensors =  sensorRepository.findAll(Sort.by("id").ascending());
        sensors.forEach(sensor -> {
            boolean statusSensor = PlantWaterService.isSensorWorking(sensor);
            sensor.setState(statusSensor);
        });
        return sensors;
    }

    public List<Sensor> findAllSensors(Integer sensorTypeId) {
        return sensorRepository.findBySensorTypeId(sensorTypeId);
    }

    public Sensor getById(Integer id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void saveOrUpdate(Sensor sensor) {
        boolean statusSensor = PlantWaterService.isSensorWorking(sensor);
        sensor.setState(statusSensor);
        sensorRepository.save(sensor);
    }

    public void delete(Integer id) {
        sensorRepository.deleteById(id);
    }

}
