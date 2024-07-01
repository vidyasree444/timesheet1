package org.imaginnovate.Repository;

import org.imaginnovate.Entity.TimesheetStatus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class TimesheetStatusRepo implements PanacheRepository<TimesheetStatus> {

    @PersistenceContext
    EntityManager em;

    public TimesheetStatus findById(Byte id) {
        return id == null ? null : em.find(TimesheetStatus.class, id);
    }

    public Object findByName(String name) {
        return name;
    }

}