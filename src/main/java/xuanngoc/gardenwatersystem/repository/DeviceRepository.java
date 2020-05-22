package xuanngoc.gardenwatersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.DeviceProperties;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    public List<Device>  findAllByGardenId(Integer gardenId);

    @Query(value = "SELECT id, name, state, status, device_type_id, garden_id, is_manual FROM device",
    nativeQuery = true)
    public List<DeviceProperties> getDevices();
}
