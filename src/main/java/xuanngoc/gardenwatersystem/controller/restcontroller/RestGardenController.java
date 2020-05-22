package xuanngoc.gardenwatersystem.controller.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.GardenProperties;
import xuanngoc.gardenwatersystem.service.*;

@RestController
@RequestMapping("/api/garden/")
public class RestGardenController {
    private GardenService gardenService;

    private PlantService plantService;

    private DeviceService deviceService;
    private DeviceTypeService deviceTypeService;

    private SensorService sensorService;
    private SensorTypeService sensorTypeService;


    @Autowired
    public void setGardenService(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Autowired
    public void setDeviceTypeService(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Autowired
    public void setSensorTypeService(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
    }

    @GetMapping("list")
    public List<GardenProperties> gardenList() {
        return gardenService.findGardens();
    }

    @GetMapping("detail/{id}")
    public Garden detailGarden(@PathVariable Integer id) {
        return gardenService.getById(id);
    }




}
















