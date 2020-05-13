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
        /*deviceRepository.findAll().forEach(device -> {
            boolean statusDevice = PlantWaterService.isDeviceWorking(device);
            // If status is fixing or broken down -> turn off
            if (!statusDevice) {
                device.setState(false); // turn off
                deviceRepository.save(device);
            }
        });*/
        return deviceRepository.findAll(Sort.by("id").ascending());
    }

    public List<Device> findAllDevices(Integer gardenId) {
        return deviceRepository.findAllByGardenId(gardenId);
    }

    public Device getById(Integer id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Device saveOrUpdate(Device device) {
        if (device.getState() == null) {
            device.setState(false);
        }
        return deviceRepository.save(device);
    }

    public void delete(Integer id) {
        deviceRepository.deleteById(id);
    }

}
