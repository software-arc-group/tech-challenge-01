package br.com.soat8.techchallenge.order.adapters.repository.entities;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.order.core.entities.enums.PaymentProgress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_snack")
public class OrderSnackEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2769090853146063559L;

    @Id
    @GeneratedValue(generator = "UUID2")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_snack_id")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID orderSnackId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "orderSnack", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderSnackItemEntity> orderSnackItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress", nullable = false)
    private OrderProgress progress;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_progress", nullable = false)
    private PaymentProgress paymentProgress;

    @JdbcTypeCode(Types.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "external_order_id", nullable = false)
    private String externalOrderId;

}
