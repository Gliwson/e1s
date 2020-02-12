package io.github.e1s.components.product;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String description;
    private String name;
    private BigDecimal price;
    private TypeMaleFemaleKid typeMaleFemaleKid;
    private Long views;

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TypeMaleFemaleKid getTypeMaleFemaleKid() {
        return typeMaleFemaleKid;
    }

    public void setTypeMaleFemaleKid(TypeMaleFemaleKid typeMaleFemaleKid) {
        this.typeMaleFemaleKid = typeMaleFemaleKid;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
