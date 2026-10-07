package me.caps123987.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "component_id", nullable = false)
    private LComponent component;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uniquetag_id", nullable = false)
    private Uniquetag uniquetag;
}
