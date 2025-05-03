CREATE TABLE Propriedade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE Laboratorio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicial TIMESTAMP NOT NULL,
    data_final TIMESTAMP NOT NULL,
    observacoes TEXT,
    propriedade_id BIGINT,
    laboratorio_id BIGINT,
    CONSTRAINT fk_usuario_propriedade FOREIGN KEY (propriedade_id) REFERENCES Propriedade(id),
    CONSTRAINT fk_usuario_laboratorio FOREIGN KEY (laboratorio_id) REFERENCES Laboratorio(id)
);
