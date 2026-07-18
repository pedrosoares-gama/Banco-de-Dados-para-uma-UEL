CREATE DATABASE IF NOT EXISTS grupo_escoteiro
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE grupo_escoteiro;

CREATE TABLE secoes (
    id_secao        INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL,          
    faixa_etaria_min TINYINT NOT NULL,
    faixa_etaria_max TINYINT NOT NULL,
    descricao       VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE patrulhas (
    id_patrulha     INT AUTO_INCREMENT PRIMARY KEY,
    id_secao        INT NOT NULL,
    nome            VARCHAR(50) NOT NULL,          
    lema            VARCHAR(100),
    CONSTRAINT fk_patrulha_secao FOREIGN KEY (id_secao)
        REFERENCES secoes(id_secao)
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE membros (
    id_membro       INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo   VARCHAR(150) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf             VARCHAR(14) UNIQUE,
    tipo_membro     ENUM('jovem', 'escotista', 'dirigente') NOT NULL DEFAULT 'jovem',
    id_secao        INT,
    id_patrulha     INT,
    telefone        VARCHAR(20),
    email           VARCHAR(120),
    nome_responsavel VARCHAR(150),                 
    telefone_responsavel VARCHAR(20),
    data_ingresso   DATE NOT NULL DEFAULT (CURRENT_DATE),
    ativo           BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_membro_secao FOREIGN KEY (id_secao)
        REFERENCES secoes(id_secao) ON DELETE SET NULL,
    CONSTRAINT fk_membro_patrulha FOREIGN KEY (id_patrulha)
        REFERENCES patrulhas(id_patrulha) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE especialidades (
    id_especialidade INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    area            VARCHAR(50),                  
    descricao       VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE membro_especialidades (
    id_membro_especialidade INT AUTO_INCREMENT PRIMARY KEY,
    id_membro       INT NOT NULL,
    id_especialidade INT NOT NULL,
    data_conquista  DATE NOT NULL,
    observacoes     VARCHAR(255),
    CONSTRAINT fk_me_membro FOREIGN KEY (id_membro)
        REFERENCES membros(id_membro) ON DELETE CASCADE,
    CONSTRAINT fk_me_especialidade FOREIGN KEY (id_especialidade)
        REFERENCES especialidades(id_especialidade) ON DELETE CASCADE,
    UNIQUE KEY uq_membro_especialidade (id_membro, id_especialidade)
) ENGINE=InnoDB;

CREATE TABLE progressao_classes (
    id_progressao   INT AUTO_INCREMENT PRIMARY KEY,
    id_membro       INT NOT NULL,
    nome_etapa      VARCHAR(100) NOT NULL,
    data_conquista  DATE NOT NULL,
    observacoes     VARCHAR(255),
    CONSTRAINT fk_progressao_membro FOREIGN KEY (id_membro)
        REFERENCES membros(id_membro) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE mensalidades (
    id_mensalidade  INT AUTO_INCREMENT PRIMARY KEY,
    id_membro       INT NOT NULL,
    mes_referencia  DATE NOT NULL,                  
    valor           DECIMAL(10,2) NOT NULL,
    status_pagamento ENUM('pendente', 'pago', 'atrasado', 'isento') NOT NULL DEFAULT 'pendente',
    data_pagamento  DATE,
    forma_pagamento ENUM('pix', 'dinheiro', 'transferencia', 'cartao', 'outro'),
    CONSTRAINT fk_mensalidade_membro FOREIGN KEY (id_membro)
        REFERENCES membros(id_membro) ON DELETE CASCADE,
    UNIQUE KEY uq_membro_mes (id_membro, mes_referencia)
) ENGINE=InnoDB;

CREATE TABLE atividades (
    id_atividade    INT AUTO_INCREMENT PRIMARY KEY,
    id_secao        INT,
    titulo          VARCHAR(150) NOT NULL,
    data_atividade  DATE NOT NULL,
    local           VARCHAR(150),
    descricao       TEXT,
    CONSTRAINT fk_atividade_secao FOREIGN KEY (id_secao)
        REFERENCES secoes(id_secao) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE presencas (
    id_presenca     INT AUTO_INCREMENT PRIMARY KEY,
    id_atividade    INT NOT NULL,
    id_membro       INT NOT NULL,
    presente        BOOLEAN NOT NULL DEFAULT FALSE,
    justificativa   VARCHAR(255),
    CONSTRAINT fk_presenca_atividade FOREIGN KEY (id_atividade)
        REFERENCES atividades(id_atividade) ON DELETE CASCADE,
    CONSTRAINT fk_presenca_membro FOREIGN KEY (id_membro)
        REFERENCES membros(id_membro) ON DELETE CASCADE,
    UNIQUE KEY uq_atividade_membro (id_atividade, id_membro)
) ENGINE=InnoDB;

CREATE TABLE eventos (
    id_evento       INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,          
    tipo            ENUM('acampamento', 'distrital', 'regional', 'nacional', 'internacional', 'outro') NOT NULL,
    data_inicio     DATE NOT NULL,
    data_fim        DATE NOT NULL,
    local           VARCHAR(200),
    valor_inscricao DECIMAL(10,2) DEFAULT 0,
    vagas_limite    INT,
    descricao       TEXT
) ENGINE=InnoDB;

CREATE TABLE inscricoes_eventos (
    id_inscricao    INT AUTO_INCREMENT PRIMARY KEY,
    id_evento       INT NOT NULL,
    id_membro       INT NOT NULL,
    data_inscricao  DATE NOT NULL DEFAULT (CURRENT_DATE),
    status_pagamento ENUM('pendente', 'pago', 'parcial', 'isento') NOT NULL DEFAULT 'pendente',
    valor_pago      DECIMAL(10,2) DEFAULT 0,
    observacoes     VARCHAR(255),
    CONSTRAINT fk_inscricao_evento FOREIGN KEY (id_evento)
        REFERENCES eventos(id_evento) ON DELETE CASCADE,
    CONSTRAINT fk_inscricao_membro FOREIGN KEY (id_membro)
        REFERENCES membros(id_membro) ON DELETE CASCADE,
    UNIQUE KEY uq_evento_membro (id_evento, id_membro)
) ENGINE=InnoDB;

CREATE INDEX idx_membros_secao ON membros(id_secao);
CREATE INDEX idx_membros_patrulha ON membros(id_patrulha);
CREATE INDEX idx_mensalidades_status ON mensalidades(status_pagamento);
CREATE INDEX idx_presencas_atividade ON presencas(id_atividade);

INSERT INTO secoes (nome, faixa_etaria_min, faixa_etaria_max, descricao) VALUES
('Alcateia', 7, 10, 'Ramo Lobinho'),
('Tropa Escoteira', 11, 14, 'Ramo Escoteiro'),
('Tropa Sênior', 15, 17, 'Ramo Sênior'),
('Clã', 18, 21, 'Ramo Pioneiro');

INSERT INTO especialidades (nome, area, descricao) VALUES
('Primeiros Socorros', 'Saúde', 'Especialidade de atendimento emergencial básico'),
('Pioneiria', 'Técnicas Escoteiras', 'Construções com cordas e madeira'),
('Meio Ambiente', 'Natureza', 'Educação ambiental e sustentabilidade');
