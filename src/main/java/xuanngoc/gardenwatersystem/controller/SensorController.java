package xuanngoc.gardenwatersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.service.DeviceService;
import xuanngoc.gardenwatersystem.service.GardenService;
import xuanngoc.gardenwatersystem.service.SensorService;
import xuanngoc.gardenwatersystem.service.SensorTypeService;

@Controller
public class SensorController {

    private SensorService sensorService;
    private SensorTypeService sensorTypeService;
    private GardenService gardenService;

    @Autowired
    public void setSensorService(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Autowired
    public void setSensorTypeService(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
    }

    @Autowired
    public void setGardenService(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @RequestMapping("sensor/list")
    public String listSensor(Model model) {
        model.addAttribute("sensors", sensorService.findAllSensorsBySensorId());
        return "sensor/list";
    }

    @RequestMapping("sensor/list/{id}")
    public String listSensor(@PathVariable Integer id, Model model) {
        model.addAttribute("sensors", sensorService.findAllSensorsBySensorId(id));
        return "sensor/list";
    }

    @RequestMapping("sensor/new")
    public String newSensor(Model model) {
        model.addAttribute("sensor", new Sensor());
        model.addAttribute("sensorTypes", sensorTypeService.findAllSensorTypes());
        model.addAttribute("gardens", gardenService.findAllGardensByPlantId());
        return "sensor/new-sensor";
    }

    @RequestMapping("sensor/edit/{id}")
    public String editSensor(@PathVariable Integer id,  Model model) {
        model.addAttribute("sensor", sensorService.getById(id));
        model.addAttribute("sensorTypes", sensorTypeService.findAllSensorTypes());
        model.addAttribute("gardens", gardenService.findAllGardensByPlantId());
        return "sensor/edit-sensor";
    }

    @RequestMapping(value = "sensor", method = RequestMethod.POST)
    public String saveOrUpdate(Sensor sensor) {
        sensorService.saveOrUpdate(sensor);
        return "redirect:/sensor/list";
    }

    @RequestMapping("sensor/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sensorService.delete(id);
        return "redirect:/sensor/list";
    }

}
