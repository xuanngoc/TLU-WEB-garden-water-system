package xuanngoc.gardenwatersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.repository.DeviceRepository;
import xuanngoc.gardenwatersystem.repository.SensorRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorService {

    private SensorRepository sensorRepository;
    private DeviceRepository deviceRepository;


    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Sensor> findAllSensorsBySensorId() {
        sensorRepository.findAll().forEach(sensor -> {
            boolean isWorking = PlantWaterService.isSensorWorking(sensor);
            // If sensor is fixing or broken down -> turn off
            if (!isWorking) {
                sensor.setState(false); //turn off
                sensorRepository.save(sensor);
            }
        });
        return sensorRepository.findAll(Sort.by("id").ascending());
    }

    public List<Sensor> findAllSensorsBySensorId(Integer sensorTypeId) {
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
