package xuanngoc.gardenwatersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorType;
import xuanngoc.gardenwatersystem.service.SensorTypeService;

@Controller
public class SensorTypeController {

    private SensorTypeService sensorTypeService;

    @Autowired
    public void setSensorTypeService(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
    }

    @RequestMapping("sensor-type/list")
    public String listSensorTypes(Model model) {
        model.addAttribute("sensorTypes", sensorTypeService.findAllSensorTypes());
        return "sensor-type/list";
    }

    @RequestMapping("sensor-type/new")
    public String newSensorType(Model model) {
        model.addAttribute("sensorType", new SensorType());
        return "sensor-type/new-sensor-type";
    }

    @RequestMapping("sensor-type/edit/{id}")
    public String editSensorType(@PathVariable Integer id, Model model) {
        model.addAttribute("sensorType", sensorTypeService.getById(id));
        return "sensor-type/edit-sensor-type";
    }

    @RequestMapping("sensor-type")
    public String saveOrUpdate(SensorType sensorType) {
        sensorTypeService.saveOrUpdate(sensorType);
        return "redirect:/sensor-type/list";
    }

    @RequestMapping("sensor-type/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sensorTypeService.delete(id);
        return "redirect:/sensor-type/list";
    }

}
