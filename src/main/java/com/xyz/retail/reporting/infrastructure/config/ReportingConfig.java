/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyz.retail.reporting.application.port.out.LoadSalesReportPort;
import com.xyz.retail.reporting.application.service.ReportingService;

@Configuration
public class ReportingConfig {

  @Bean
  public ReportingService reportingService(LoadSalesReportPort loadSalesReportPort) {
    return new ReportingService(loadSalesReportPort);
  }
}
