package xuanngoc.gardenwatersystem.controller.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorProperties;
import xuanngoc.gardenwatersystem.service.SensorService;

import java.util.List;

@RestController
@RequestMapping("/api/sensor/")
public class RestSensorController {

    private SensorService sensorService;

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/list")
    public List<SensorProperties> sensorList() {
        return sensorService.findAll();
    }

    @PostMapping("update/state/{id}")
    public void updateState(@PathVariable Integer id) {
        Sensor sensor = sensorService.getById(id);
        sensor.setState(!sensor.getState());
        sensorService.updateState(sensor);
    }



}
