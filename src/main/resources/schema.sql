

CREATE TABLE PORTFOLIO_ITEMS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    asset_id VARCHAR(255) NOT NULL,
    asset_name VARCHAR(255) NOT NULL,
    asset_symbol VARCHAR(255) NOT NULL,
    quantity DECIMAL(19, 8) NOT NULL,
    purchase_price DECIMAL(19, 8) NOT NULL,
    current_price DECIMAL(19, 8) NOT NULL,
    amount_invested DECIMAL(19, 8) NOT NULL,
    purchase_date TIMESTAMP NOT NULL
);