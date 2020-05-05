package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.repository.PlantRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        return plantRepository.findAll(Sort.by("Id").ascending());
    }

    public Plant getById(Integer id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Plant saveOrUpdate(Plant plant) {
        return plantRepository.save(plant);
    }

    public void delete(Integer id) {
        plantRepository.deleteById(id);
    }
}
