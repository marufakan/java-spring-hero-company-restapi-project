package com.works.repostories;

import com.works.entities.OrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderReportRepository extends JpaRepository<OrderReport, Long> {
    @Query(value = "select o.id,created_by,total from orders o inner join basket b on o.uuid = b.uuid group by b.uuid", nativeQuery = true)
    List<OrderReport> ordersCustomerReport();

}