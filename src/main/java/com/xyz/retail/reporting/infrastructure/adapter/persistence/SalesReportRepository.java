/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReportRepository extends JpaRepository<SalesReportJpaEntity, Long> {

  @Query(
      "SELECT sr FROM SalesReportJpaEntity sr WHERE sr.date = :date "
          + "ORDER BY sr.quantitySold DESC, sr.totalSales DESC")
  List<SalesReportJpaEntity> findTopSellingByDate(LocalDate date);

  @Query(
      "SELECT sr FROM SalesReportJpaEntity sr WHERE sr.date BETWEEN :startDate AND :endDate "
          + "GROUP BY sr.productId, sr.productName "
          + "ORDER BY SUM(sr.quantitySold) ASC, SUM(sr.totalSales) ASC")
  List<SalesReportJpaEntity> findLeastSellingByDateRange(LocalDate startDate, LocalDate endDate);

  @Query(
      "SELECT sr FROM SalesReportJpaEntity sr WHERE sr.date BETWEEN :startDate AND :endDate "
          + "ORDER BY sr.date")
  List<SalesReportJpaEntity> findByDateRange(LocalDate startDate, LocalDate endDate);

  @Query("SELECT SUM(sr.totalSales) FROM SalesReportJpaEntity sr WHERE sr.date = :date")
  BigDecimal findTotalSalesByDate(LocalDate date);

  @Query("SELECT COUNT(DISTINCT sr.id) FROM SalesReportJpaEntity sr WHERE sr.date = :date")
  int findOrderCountByDate(LocalDate date);
}
