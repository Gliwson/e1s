package io.github.e1s.e1s.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(value = "male", nullable = false)
    private Long male;

    @NotNull
    @Column(value = "female", nullable = false)
    private Long female;

    @NotNull
    @Column(value = "kid", nullable = false)
    private Long kid;
}
