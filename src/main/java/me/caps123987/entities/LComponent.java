package me.caps123987.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lcomponents")
public class LComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Lob
    @Column(name = "code", nullable = false)
    private String code;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @OneToMany(mappedBy = "component")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "component")
    private Set<Like> likes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "component")
    private Set<Tag> tags = new LinkedHashSet<>();


}