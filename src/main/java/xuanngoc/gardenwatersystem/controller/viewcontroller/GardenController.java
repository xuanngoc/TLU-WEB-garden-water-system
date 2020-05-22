package xuanngoc.gardenwatersystem.controller.viewcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.service.*;

@Controller
public class GardenController {

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

    @RequestMapping("garden/list")
    public String listGardens(Model model) {
        model.addAttribute("gardens", gardenService.findAllGardens());
        return "garden/list";
    }

    @RequestMapping("garden/list/{plantId}")
    public String listGardens(@PathVariable Integer plantId, Model model) {
        model.addAttribute("gardens", gardenService.findAllGardensByPlantId(plantId));
        return "garden/list";
    }

    @RequestMapping("garden/new")
    public String newGarden(Model model) {
        model.addAttribute("garden", new Garden());
        model.addAttribute("plants", plantService.getAllPlants());
        return "garden/new-garden";
    }

    @RequestMapping("garden/edit/{id}")
    public String editGarden(@PathVariable Integer id, Model model) {
        model.addAttribute("garden", gardenService.getById(id));
        model.addAttribute("plants", plantService.getAllPlants());
        return "garden/edit-garden";
    }

    @RequestMapping("garden/detail/{id}")
    public String detailGarden(@PathVariable Integer id, Model model) {
        model.addAttribute("garden", gardenService.getById(id));
        return "garden/detail-garden";
    }

    // Garden
    @RequestMapping("garden/{gardenId}/device/new")
    public String newDeviceFromGarden(@PathVariable Integer gardenId, Model model) {
        Device device = new Device();
        device.setGarden(gardenService.getById(gardenId));

        // Set manual is false by default. It's mean devices are working automatic.
        device.setManual(false);
        model.addAttribute("device", device);
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        return "device/new-device-from-garden";
    }

    @RequestMapping("garden/{gardenId}/device/update/{deviceId}")
    public String updateDeviceFromGarden(@PathVariable Integer deviceId, Model model) {
        Device device = deviceService.getById(deviceId);
        model.addAttribute("device", device);
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        return "device/update-from-garden";
    }

    @RequestMapping("garden/{gardenId}/device/delete/{deviceId}")
    public String deleteDeviceFromGarden(@PathVariable Integer gardenId, @PathVariable Integer deviceId) {
       deviceService.delete(deviceId);
        return "redirect:/garden/detail/" + gardenId.toString();
    }

    @RequestMapping("garden/{gardenId}/device/save")
    public String saveDeviceFromGarden(@PathVariable Integer gardenId, Device device) {
        deviceService.saveOrUpdate(device);
        return "redirect:garden/detail/" + gardenId.toString();
    }


    // Sensor
    @RequestMapping("garden/{gardenId}/sensor/new")
    public String newSensorFromGarden(@PathVariable Integer gardenId, Model model) {
        Sensor sensor = new Sensor();
        sensor.setGarden(gardenService.getById(gardenId));
        model.addAttribute("sensor", sensor);
        model.addAttribute("sensorTypes", sensorTypeService.findAllSensorTypes());
        return "sensor/new-sensor-from-garden";
    }

    @RequestMapping("garden/{gardenId}/sensor/update/{sensorId}")
    public String updateSensorFromGarden(@PathVariable Integer sensorId, Model model) {
        Sensor sensor = sensorService.getById(sensorId);
        model.addAttribute("sensor", sensor);
        model.addAttribute("sensorTypes", sensorTypeService.findAllSensorTypes());
        return "sensor/update-from-garden";
    }

    @RequestMapping("garden/{gardenId}/sensor/delete/{sensorId}")
    public String deleteSenorFromGarden(@PathVariable Integer sensorId, @PathVariable Integer deviceId) {
        deviceService.delete(deviceId);
        return "redirect:/garden/detail/" + sensorId.toString();
    }

    @RequestMapping("garden/{gardenId}/sensor/save")
    public String saveSensorFromGarden(@PathVariable Integer gardenId, Sensor sensor) {
        sensorService.saveOrUpdate(sensor);
        return "redirect:/garden/detail/" + gardenId.toString();
    }


    @RequestMapping(value = "garden", method = RequestMethod.POST)
    public String saveOrUpdate(Garden garden) {
        Garden saveGarden = gardenService.saveOrUpdate(garden);
        return "redirect:/garden/list";
    }

    @RequestMapping(value = "garden/delete/{id}")
    public String delete(@PathVariable Integer id) {
        gardenService.delete(id);
        return "redirect:/garden/list";
    }
}
