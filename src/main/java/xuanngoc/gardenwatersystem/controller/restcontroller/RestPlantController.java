package xuanngoc.gardenwatersystem.controller.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.service.PlantService;

import java.util.List;

@RestController
@RequestMapping("/api/plant/")
public class RestPlantController {

    private PlantService plantService;

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/list")
    public List<Plant> plantList() {
        return plantService.getAllPlants();
    }

    @GetMapping("/detail/{id}")
    public Plant detailPlant(@PathVariable Integer id) {
        return plantService.getById(id);
    }

}
