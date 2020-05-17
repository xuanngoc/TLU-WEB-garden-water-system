package xuanngoc.gardenwatersystem.controller.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.service.SensorService;

@RestController
@RequestMapping("/api/sensor/")
public class RestSensorController {

    private SensorService sensorService;

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("update/state/{id}")
    public void updateState(@PathVariable Integer id) {
        Sensor sensor = sensorService.getById(id);
        sensor.setState(!sensor.getState());
        sensorService.saveOrUpdate(sensor);
    }

}
