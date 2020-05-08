package xuanngoc.gardenwatersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    public List<Device>  findAllByGardenId(Integer gardenId);
}
