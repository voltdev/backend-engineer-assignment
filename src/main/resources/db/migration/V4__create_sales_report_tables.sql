CREATE TABLE sales_report (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              date DATE NOT NULL,
                              product_id UUID NOT NULL,
                              product_name VARCHAR(255) NOT NULL,
                              quantity_sold INT NOT NULL,
                              total_sales DECIMAL(19, 2) NOT NULL
);

CREATE INDEX idx_sales_report_date ON sales_report (date);
CREATE INDEX idx_sales_report_product_id ON sales_report (product_id);