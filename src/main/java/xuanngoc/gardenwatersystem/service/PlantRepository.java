package xuanngoc.gardenwatersystem.service;

import org.springframework.stereotype.Repository;
import xuanngoc.gardenwatersystem.model.Plant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class PlantRepository implements PlantDao{

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Plant> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Plant", Plant.class).getResultList();
    }

    @Override
    public Plant getById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Plant.class, id);
    }

    @Override
    public Plant saveOrUpdate(Plant plant) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Plant savePlant = entityManager.merge(plant);
        entityManager.getTransaction().commit();
        return savePlant;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Plant.class, id));
        entityManager.getTransaction().commit();
    }
}
