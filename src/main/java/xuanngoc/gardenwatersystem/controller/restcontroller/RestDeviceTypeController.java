package xuanngoc.gardenwatersystem.controller.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.DeviceType;
import xuanngoc.gardenwatersystem.service.DeviceTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/device-type")
public class RestDeviceTypeController {

    private DeviceTypeService deviceTypeService;

    @Autowired
    public void setDeviceTypeService(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping("/list")
    public List<DeviceType> deviceTypeList() {
        return deviceTypeService.findAllDeviceTypes();
    }

    @GetMapping("/detail/{id}")
    public DeviceType detailDeviceType(@PathVariable Integer id) {
        return deviceTypeService.findById(id);
    }
}
