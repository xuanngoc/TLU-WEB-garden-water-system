package xuanngoc.gardenwatersystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.GardenProperties;
import xuanngoc.gardenwatersystem.model.Plant;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Integer> {

    public List<Garden> findByPlantId(Integer Id);

    @Query(value = "SELECT id, name, plant_id, area FROM garden", nativeQuery = true)
    public List<GardenProperties> findAllGardenProperties();
}
