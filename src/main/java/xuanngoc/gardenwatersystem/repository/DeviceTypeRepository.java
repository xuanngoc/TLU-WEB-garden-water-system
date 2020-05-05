package xuanngoc.gardenwatersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.DeviceType;
@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {
}
