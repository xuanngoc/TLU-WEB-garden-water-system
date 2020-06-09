package xuanngoc.gardenwatersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorProperties;
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
        return sensorRepository.findAll(Sort.by("id").ascending());
    }

    public List<Sensor> findAllSensorsBySensorId(Integer sensorTypeId) {
        return sensorRepository.findBySensorTypeId(sensorTypeId);
    }

    public List<SensorProperties> findAll() {
        return sensorRepository.getSensors();
    }

    public Sensor getById(Integer id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void saveOrUpdate(Sensor sensor) {
        if (!sensor.getStatus().equals(PlantWaterService.WORKING)) {
            sensor.setState(false);
        }
        sensorRepository.save(sensor);
    }

    public void delete(Integer id) {
        sensorRepository.deleteById(id);
    }

}
