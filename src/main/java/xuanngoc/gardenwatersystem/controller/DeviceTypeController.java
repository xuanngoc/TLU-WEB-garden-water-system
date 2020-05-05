package xuanngoc.gardenwatersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.DeviceType;
import xuanngoc.gardenwatersystem.service.DeviceTypeService;

@Controller
public class DeviceTypeController {

    private DeviceTypeService deviceTypeService;

    @Autowired
    public void setDeviceTypeService(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @RequestMapping("device-type/list")
    public String listDeviceTypes(Model model) {
        model.addAttribute("deviceTypes", deviceTypeService.findAllDeviceTypes());
        return "device-type/list";
    }

    @RequestMapping("device-type/new")
    public String newDeviceType(Model model) {
        model.addAttribute("deviceType", new DeviceType());
        return "device-type/new-device-type";
    }

    @RequestMapping("device-type/edit/{id}")
    public String editDeviceType(@PathVariable Integer id, Model model) {
        model.addAttribute("deviceType", deviceTypeService.findById(id));
        return "device-type/edit-device-type";
    }

    @RequestMapping(value = "device-type", method = RequestMethod.POST)
    public String saveOrUpdate(DeviceType deviceType) {
        deviceTypeService.saveOrUpdate(deviceType);
        return "redirect:/device-type/list";
    }

    @RequestMapping("device-type/delete/{id}")
    public String delete(@PathVariable Integer id) {
        deviceTypeService.delete(id);
        return "redirect:/device-type/list";
    }

}
