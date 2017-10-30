package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.*;

/**
 * Created by Dmitry on 30.10.2017.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
