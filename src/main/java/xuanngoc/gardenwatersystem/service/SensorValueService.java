package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.SensorValue;
import xuanngoc.gardenwatersystem.repository.DeviceRepository;
import xuanngoc.gardenwatersystem.repository.SensorValueRepository;

import javax.persistence.EntityNotFoundException;


@Service
public class SensorValueService {

    private SensorValueRepository sensorValueRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public void setSensorValueRepository(SensorValueRepository sensorValueRepository) {
        this.sensorValueRepository = sensorValueRepository;
    }

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
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
        Garden garden = sensorValue.getSensor().getGarden();
        boolean stateDevice = PlantWaterService.plantWater(sensorValue.getValue(), garden.getPlant());
        deviceRepository.findAllByGardenId(garden.getId()).forEach(device -> {
            device.setState(stateDevice);
        });
        return sensorValueRepository.save(sensorValue);
    }

    public void delete(Integer id) {
        sensorValueRepository.deleteById(id);
    }

}
