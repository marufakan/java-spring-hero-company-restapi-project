package com.works.repostories;

import com.works.entities.OrderReport;
import com.works.entities.OrderReportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderReportDetailsRepository extends JpaRepository<OrderReportDetails, Long> {
    @Query(value = "select b.id as id, b.created_by as created_by, p.name as product , c.name as category, count, b.created_date as created_date from orders o inner join basket b on o.uuid = b.uuid  inner join category c on b.category_id = c.category_id inner join product p on b.pid = p.pid where status=0", nativeQuery = true)
    List<OrderReportDetails> oRDetails();
}