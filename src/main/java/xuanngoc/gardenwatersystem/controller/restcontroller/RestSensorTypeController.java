package xuanngoc.gardenwatersystem.controller.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.SensorType;
import xuanngoc.gardenwatersystem.service.SensorTypeService;


import java.util.List;

@RestController
@RequestMapping("api/sensor-type/")
public class RestSensorTypeController {

    private SensorTypeService sensorTypeService;

    @Autowired
    public void setSensorTypeService(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
    }

    @RequestMapping("list")
    public List<SensorType> sensorTypeList() {
        return sensorTypeService.findAllSensorTypes();
    }

}
