package xuanngoc.gardenwatersystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.DeviceType;
import xuanngoc.gardenwatersystem.repository.DeviceTypeRepository;

import javax.persistence.EntityExistsException;

@Service
public class DeviceTypeService {

    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    public void setDeviceTypeRepository(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public List<DeviceType> findAllDeviceTypes() {
        return deviceTypeRepository.findAll(Sort.by("Id").ascending());
    }

    public DeviceType findById(Integer id) {
        return deviceTypeRepository.findById(id)
                .orElseThrow(() ->new EntityExistsException(id.toString()));
    }

    public DeviceType saveOrUpdate(DeviceType deviceType) {
        return deviceTypeRepository.save(deviceType);
    }

    public void delete(Integer id) {
        deviceTypeRepository.deleteById(id);
    }

}
