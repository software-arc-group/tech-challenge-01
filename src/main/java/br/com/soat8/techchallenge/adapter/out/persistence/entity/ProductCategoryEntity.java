package br.com.soat8.techchallenge.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(generator = "UUID2")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_category_id")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID productCategoryId;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products;
}