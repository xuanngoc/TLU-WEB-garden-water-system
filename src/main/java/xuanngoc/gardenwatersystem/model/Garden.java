package xuanngoc.gardenwatersystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Garden.class)
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String area;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @OneToMany(
            mappedBy = "garden",
            cascade = CascadeType.ALL
    )
    private List<Device> devices;

    @OneToMany(
            mappedBy = "garden",
            cascade = CascadeType.ALL
    )
    private List<Sensor> sensors;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "Garden{" +
                "id=" + id +

                ", plant=" + plant.getId() +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
