package xuanngoc.gardenwatersystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("update/state/{deviceId}")
    public void updateState(@PathVariable Integer deviceId) {
        Device device = deviceService.getById(deviceId);
        device.setState(!device.getState());
        deviceService.saveOrUpdate(device);
    }

    @GetMapping("{id}/state")
    public boolean getDeviceState(@PathVariable Integer id) {
        return deviceService.getById(id).getState();
    }


}
