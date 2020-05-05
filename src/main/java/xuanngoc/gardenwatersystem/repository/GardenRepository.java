package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.Plant;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Integer> {

    public List<Garden> findByPlantId(Integer Id);
}
