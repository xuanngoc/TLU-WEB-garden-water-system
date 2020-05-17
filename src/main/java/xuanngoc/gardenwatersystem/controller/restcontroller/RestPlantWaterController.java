package xuanngoc.gardenwatersystem.controller.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.service.PlantWaterService;

@RestController
@RequestMapping("/api/plant-water/")
public class RestPlantWaterController {

    private PlantWaterService plantWaterService;

    @Autowired
    public void setPlantWaterService(PlantWaterService plantWaterService) {
        this.plantWaterService = plantWaterService;
    }

    @PutMapping("/{gardenId}")
    public void plantWater(@PathVariable Integer gardenId) {
        plantWaterService.plantWater(gardenId);
    }

}
