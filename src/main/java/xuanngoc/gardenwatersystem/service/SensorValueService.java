package xuanngoc.gardenwatersystem.service;

import java.util.Date;
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

    public List<Date> findDatesBySensorId(Integer id) {
        return sensorValueRepository.findDatesBySensorId(id);
    }

    public SensorValue findById(Integer id) {
        return sensorValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public SensorValue saveOrUpdate(SensorValue sensorValue) {
        Garden garden = sensorValue.getSensor().getGarden();
//        sensorValue.getSensor().getSensorType()
        int stateDevice = PlantWaterService.plantWater(sensorValue.getValue(), garden.getPlant());
        deviceRepository.findAllByGardenId(garden.getId()).forEach(device -> {
            if (device.getStatus().equals(PlantWaterService.WORKING_AUTO)){
                if (stateDevice == 1) {
                    device.setState(true);
                } else if(stateDevice == 0) {
                    device.setState(false);
                }
                // else state = currentState

            }
        });
        return sensorValueRepository.save(sensorValue);
    }

    public void delete(Integer id) {
        sensorValueRepository.deleteById(id);
    }

}
