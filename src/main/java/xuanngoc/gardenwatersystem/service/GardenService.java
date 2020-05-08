package xuanngoc.gardenwatersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.repository.GardenRepository;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class GardenService {

    @Autowired
    private GardenRepository gardenRepository;

    public List<Garden> findAllGardensByPlantId() {
        return gardenRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Garden> findAllGardensByPlantId(Integer id) {
        return gardenRepository.findByPlantId(id);
    }

    public Garden getById(Integer id) {
        return gardenRepository.findById(id)
                .orElseThrow(() ->new EntityExistsException(id.toString()));
    }

    public Garden saveOrUpdate(Garden garden) {
        return gardenRepository.save(garden);
    }

    public void delete(Integer id) {
        gardenRepository.deleteById(id);
    }

}
