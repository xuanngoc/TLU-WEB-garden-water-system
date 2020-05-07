package xuanngoc.gardenwatersystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.service.DeviceService;

@RestController
@RequestMapping("/api/device/")
public class RestDeviceController {

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("{id}")
    public Device getDevice(@PathVariable Integer id) {
        return deviceService.getById(id);
    }

}
