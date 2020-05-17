package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.SensorType;
import xuanngoc.gardenwatersystem.repository.SensorTypeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorTypeService {

    private SensorTypeRepository sensorTypeRepository;

    public SensorTypeRepository getSensorTypeRepository() {
        return sensorTypeRepository;
    }

    @Autowired
    public void setSensorTypeRepository(SensorTypeRepository sensorTypeRepository) {
        this.sensorTypeRepository = sensorTypeRepository;
    }

    public List<SensorType> findAllSensorTypes() {
        return sensorTypeRepository.findAll(Sort.by("id").ascending());
    }

    public SensorType getById(Integer id) {
        return sensorTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public SensorType findByName(String name) {
        return sensorTypeRepository.findByName(name);
    }

    public void saveOrUpdate(SensorType sensorType) {
        sensorTypeRepository.save(sensorType);
    }

    public void delete(Integer id) {
        sensorTypeRepository.deleteById(id);
    }

}
