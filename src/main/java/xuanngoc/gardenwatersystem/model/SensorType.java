package xuanngoc.gardenwatersystem.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Sensor> sensors;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
