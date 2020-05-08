package xuanngoc.gardenwatersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.service.DeviceService;
import xuanngoc.gardenwatersystem.service.DeviceTypeService;
import xuanngoc.gardenwatersystem.service.GardenService;

@Controller
public class DeviceController {

    private DeviceService deviceService;
    private DeviceTypeService deviceTypeService;
    private GardenService gardenService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Autowired
    public void setDeviceTypeService(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @Autowired
    public void setGardenService(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @RequestMapping("device/list")
    public String listDevices(Model model) {
        model.addAttribute("devices", deviceService.findAllDevices());
        return "device/list";
    }

    @RequestMapping("device/new")
    public String newDevice(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        model.addAttribute("gardens", gardenService.findAllGardensByPlantId());
        return "device/new-device";
    }

    @RequestMapping("device/edit/{id}")
    public String editDevice(@PathVariable Integer id,  Model model) {
        model.addAttribute("device", deviceService.getById(id));
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        model.addAttribute("gardens", gardenService.findAllGardensByPlantId());
        return "device/edit-device";
    }

    @RequestMapping(value = "device", method = RequestMethod.POST)
    public String saveOrUpdate(Device device) {
        deviceService.saveOrUpdate(device);
        return "redirect:/device/list";
    }

    @RequestMapping("device/delete/{id}")
    public String delete(@PathVariable Integer id) {
        deviceService.delete(id);
        return "redirect:/device/list";
    }




}
