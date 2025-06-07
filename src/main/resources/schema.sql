CREATE TABLE IF NOT EXISTS technology (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS capability_technology (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    capability_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    FOREIGN KEY (technology_id) REFERENCES technology(id)
);