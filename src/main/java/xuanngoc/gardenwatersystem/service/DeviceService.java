package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.repository.DeviceRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAllDevices() {
        List<Device> devices =  deviceRepository.findAll(Sort.by("id").ascending());
        devices.forEach(device -> {
            boolean statusDevice = PlantWaterService.checkStatusDevice(device);
            device.setState(statusDevice);
        });
        return devices;
    }

    public Device getById(Integer id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Device saveOrUpdate(Device device) {
        return deviceRepository.save(device);
    }

    public void delete(Integer id) {
        deviceRepository.deleteById(id);
    }

}
