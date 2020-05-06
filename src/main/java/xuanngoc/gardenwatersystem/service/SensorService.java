package xuanngoc.gardenwatersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.repository.SensorRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorService {

    private SensorRepository sensorRepository;

    @Autowired
    public void setSensorRepository(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll(Sort.by("id").ascending());
    }

    public List<Sensor> findAllSensors(Integer sensorTypeId) {
        return sensorRepository.findBySensorTypeId(sensorTypeId);
    }

    public Sensor getById(Integer id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void saveOrUpdate(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public void delete(Integer id) {
        sensorRepository.deleteById(id);
    }

}
