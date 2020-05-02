package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Plant;

@Service
public class PlantService implements CRUDService<Plant> {

    @Autowired
    private PlantDao plantDao;

    @Override
    public List<Plant> listAll() {
        return plantDao.getAll();
    }

    @Override
    public Plant getById(Integer id) {
        return plantDao.getById(id);
    }

    @Override
    public Plant saveOrUpdate(Plant plant) {
        return plantDao.saveOrUpdate(plant);
    }

    @Override
    public void delete(Integer id) {
        plantDao.delete(id);
    }
}
