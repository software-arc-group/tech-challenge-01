package br.com.soat8.techchallenge.adapter.out.persistence.entity;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "order_snack")
public class OrderSnackEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID orderSnackId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "orderSnack", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderSnackItemEntity> orderSnackItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress", nullable = false)
    private OrderProgress progress;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
