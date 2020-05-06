package xuanngoc.gardenwatersystem.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class SensorValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Sensor sensor;

    private Double value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

}
