package xuanngoc.gardenwatersystem.model;

import javax.persistence.*;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private SensorType sensorType;

    @OneToOne
    private Device device;

    private String name;
    private String status;
    private Boolean state;
}
