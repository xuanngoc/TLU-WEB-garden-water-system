package xuanngoc.gardenwatersystem.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorValue;
import xuanngoc.gardenwatersystem.service.PlantWaterService;
import xuanngoc.gardenwatersystem.service.SensorService;
import xuanngoc.gardenwatersystem.service.SensorValueService;

@RestController
@RequestMapping("/api/sensor-value/")
public class RestSensorValueController {

    private SensorValueService sensorValueService;
    private SensorService sensorService;

    @Autowired
    public void setSensorValueService(SensorValueService sensorValueService) {
        this.sensorValueService = sensorValueService;
    }

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @RequestMapping("/list/{id}")
    private List<SensorValue> listAllSensorValues(@PathVariable Integer id) {
        return sensorValueService.findAllSensorValues(id);
    }

    @PostMapping("/{id}/new")
    private ResponseEntity newSensorValue(@PathVariable Integer id, @RequestBody SensorValue sensorValue) {
        Sensor sensor = sensorService.getById(id);
        if (PlantWaterService.isSensorWorking(sensor)) {
            sensorValue.setSensor(sensor);
            sensorValueService.saveOrUpdate(sensorValue);
            return new ResponseEntity<SensorValue>(sensorValue, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
