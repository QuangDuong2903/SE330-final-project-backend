package com.quangduong.SE330backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "label")
public class LabelEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "value")
    private List<LabelAttributeEntity> labelAttributes = new ArrayList<>();
}
