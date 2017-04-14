/**
 * Author:  Lucas
 * Created: 04/03/2017
 */


CREATE SEQUENCE uf_iduf_seq;

CREATE TABLE Uf (
                idUf INTEGER NOT NULL DEFAULT nextval('uf_iduf_seq'),
                nome VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) DEFAULT 'A' NOT NULL,
                sigla CHAR(2) DEFAULT '' NOT NULL,
                CONSTRAINT uf_pk PRIMARY KEY (idUf)
);
COMMENT ON COLUMN Uf.situacao IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE uf_iduf_seq OWNED BY Uf.idUf;

CREATE SEQUENCE funcao_idfuncao_seq;

CREATE TABLE Funcao (
                idFuncao BIGINT NOT NULL DEFAULT nextval('funcao_idfuncao_seq'),
                descricao VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) DEFAULT 'A' NOT NULL,
                CONSTRAINT funcao_pk PRIMARY KEY (idFuncao)
);
COMMENT ON COLUMN Funcao.situacao IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE funcao_idfuncao_seq OWNED BY Funcao.idFuncao;

CREATE SEQUENCE estrutura_idestrutura_seq;

CREATE TABLE Estrutura (
                idEstrutura BIGINT NOT NULL DEFAULT nextval('estrutura_idestrutura_seq'),
                idEstruturaPai BIGINT NOT NULL,
                descricao VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) DEFAULT 'A' NOT NULL,
                tempoMaximoAbertura INTEGER DEFAULT 60 NOT NULL,
                foto BYTEA,
                fotoExtensao VARCHAR(16),
                CONSTRAINT estrutura_pk PRIMARY KEY (idEstrutura)
);
COMMENT ON COLUMN Estrutura.situacao IS 'A-Ativa,I-Inativa';
COMMENT ON COLUMN Estrutura.tempoMaximoAbertura IS 'Tempo máximo entre a abertura e o fechamento. Em segundos.';


ALTER SEQUENCE estrutura_idestrutura_seq OWNED BY Estrutura.idEstrutura;

CREATE TABLE Cofre (
                idEstrutura BIGINT NOT NULL,
                CONSTRAINT cofre_pk PRIMARY KEY (idEstrutura)
);


CREATE TABLE Ambiente (
                idEstrutura BIGINT NOT NULL,
                quantidadeMaximaPessoas INTEGER DEFAULT -1 NOT NULL,
                CONSTRAINT ambiente_pk PRIMARY KEY (idEstrutura)
);
COMMENT ON COLUMN Ambiente.quantidadeMaximaPessoas IS '-1-Sem controle';


CREATE TABLE Permissao (
                idPermissao BIGINT NOT NULL,
                idEstrutura BIGINT NOT NULL,
                herda BOOLEAN DEFAULT false NOT NULL,
                gerencia BOOLEAN DEFAULT false NOT NULL,
                monitora BOOLEAN DEFAULT false NOT NULL,
                abre BOOLEAN DEFAULT false NOT NULL,
                fecha BOOLEAN NOT NULL,
                diasSemana CHAR(7) DEFAULT '0000000' NOT NULL,
                meses CHAR(12) DEFAULT '000000000000' NOT NULL,
                horaInicio TIME DEFAULT current_time NOT NULL,
                horaFim TIME DEFAULT current_time NOT NULL,
                dataInicio DATE DEFAULT current_date NOT NULL,
                dataFim DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissao_pk PRIMARY KEY (idPermissao)
);


CREATE SEQUENCE permissaofuncao_idpermissaofuncao_seq;

CREATE TABLE PermissaoFuncao (
                idPermissaoFuncao BIGINT NOT NULL DEFAULT nextval('permissaofuncao_idpermissaofuncao_seq'),
                idPermissao BIGINT NOT NULL,
                idFuncao BIGINT NOT NULL,
                dataInicio DATE DEFAULT current_date NOT NULL,
                dataFim DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissaofuncao_pk PRIMARY KEY (idPermissaoFuncao)
);


ALTER SEQUENCE permissaofuncao_idpermissaofuncao_seq OWNED BY PermissaoFuncao.idPermissaoFuncao;

CREATE SEQUENCE pessoa_idpessoa_seq;

CREATE TABLE Pessoa (
                idPessoa BIGINT NOT NULL DEFAULT nextval('pessoa_idpessoa_seq'),
                nome VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) DEFAULT 'A' NOT NULL,
                cpf VARCHAR(32),
                rg INTEGER,
                idUfRg INTEGER,
                foto BYTEA,
                fotoExtensao VARCHAR(16),
                CONSTRAINT pessoa_pk PRIMARY KEY (idPessoa)
);
COMMENT ON COLUMN Pessoa.situacao IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE pessoa_idpessoa_seq OWNED BY Pessoa.idPessoa;

CREATE TABLE FuncaoPessoa (
                idFuncaoPessoa INTEGER NOT NULL,
                idFuncao BIGINT NOT NULL,
                idPessoa BIGINT NOT NULL,
                dataInicio DATE DEFAULT current_date NOT NULL,
                dataFim DATE DEFAULT current_date NOT NULL,
                CONSTRAINT funcaopessoa_pk PRIMARY KEY (idFuncaoPessoa)
);


CREATE SEQUENCE permissaopessoa_permissaopessoa_seq;

CREATE TABLE PermissaoPessoa (
                PermissaoPessoa BIGINT NOT NULL DEFAULT nextval('permissaopessoa_permissaopessoa_seq'),
                idPermissao BIGINT NOT NULL,
                idPessoa BIGINT NOT NULL,
                dataInicio DATE DEFAULT current_date NOT NULL,
                dataFim DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissaopessoa_pk PRIMARY KEY (PermissaoPessoa)
);


ALTER SEQUENCE permissaopessoa_permissaopessoa_seq OWNED BY PermissaoPessoa.PermissaoPessoa;

CREATE SEQUENCE chave_idchave_seq;

CREATE TABLE Chave (
                idChave BIGINT NOT NULL DEFAULT nextval('chave_idchave_seq'),
                idPessoa BIGINT NOT NULL,
                descricao VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) DEFAULT 'A' NOT NULL,
                dataInicio DATE DEFAULT current_date NOT NULL,
                dataFim DATE DEFAULT current_date NOT NULL,
                CONSTRAINT chave_pk PRIMARY KEY (idChave)
);
COMMENT ON COLUMN Chave.situacao IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE chave_idchave_seq OWNED BY Chave.idChave;

CREATE SEQUENCE acesso_idacesso_seq;

CREATE TABLE Acesso (
                idAcesso BIGINT NOT NULL DEFAULT nextval('acesso_idacesso_seq'),
                tipo CHAR(1) NOT NULL,
                idPessoa BIGINT NOT NULL,
                idChave BIGINT NOT NULL,
                idEstrutura BIGINT NOT NULL,
                idPermissao BIGINT NOT NULL,
                dataHoraAbertura TIMESTAMP DEFAULT current_timestamp,
                dataHoraFechamento TIMESTAMP DEFAULT current_timestamp,
                CONSTRAINT acesso_pk PRIMARY KEY (idAcesso)
);
COMMENT ON COLUMN Acesso.tipo IS 'E-Entrada,S-Saida,A-Abertura';


ALTER SEQUENCE acesso_idacesso_seq OWNED BY Acesso.idAcesso;

CREATE TABLE Senha (
                idChave BIGINT NOT NULL,
                CONSTRAINT senha_pk PRIMARY KEY (idChave)
);


CREATE TABLE PIN (
                idChave BIGINT NOT NULL,
                CONSTRAINT pin_pk PRIMARY KEY (idChave)
);


CREATE TABLE QRCode (
                idChave BIGINT NOT NULL,
                CONSTRAINT qrcode_pk PRIMARY KEY (idChave)
);


ALTER TABLE Pessoa ADD CONSTRAINT uf_pessoa_fk
FOREIGN KEY (idUfRg)
REFERENCES Uf (idUf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PermissaoFuncao ADD CONSTRAINT funcao_permissaofuncao_fk
FOREIGN KEY (idFuncao)
REFERENCES Funcao (idFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE FuncaoPessoa ADD CONSTRAINT funcao_funcaopessoa_fk
FOREIGN KEY (idFuncao)
REFERENCES Funcao (idFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Permissao ADD CONSTRAINT estrutura_permissao_fk
FOREIGN KEY (idEstrutura)
REFERENCES Estrutura (idEstrutura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Estrutura ADD CONSTRAINT estrutura_estrutura_fk
FOREIGN KEY (idEstruturaPai)
REFERENCES Estrutura (idEstrutura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Acesso ADD CONSTRAINT estrutura_acesso_fk
FOREIGN KEY (idEstrutura)
REFERENCES Estrutura (idEstrutura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Ambiente ADD CONSTRAINT estrutura_ambiente_fk
FOREIGN KEY (idEstrutura)
REFERENCES Estrutura (idEstrutura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Cofre ADD CONSTRAINT estrutura_cofre_fk
FOREIGN KEY (idEstrutura)
REFERENCES Estrutura (idEstrutura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PermissaoPessoa ADD CONSTRAINT permissao_permissaopessoa_fk
FOREIGN KEY (idPermissao)
REFERENCES Permissao (idPermissao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PermissaoFuncao ADD CONSTRAINT permissao_permissaofuncao_fk
FOREIGN KEY (idPermissao)
REFERENCES Permissao (idPermissao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Acesso ADD CONSTRAINT permissao_acesso_fk
FOREIGN KEY (idPermissao)
REFERENCES Permissao (idPermissao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Chave ADD CONSTRAINT pessoa_chave_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PermissaoPessoa ADD CONSTRAINT pessoa_permissaopessoa_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Acesso ADD CONSTRAINT pessoa_acesso_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE FuncaoPessoa ADD CONSTRAINT pessoa_funcaopessoa_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE QRCode ADD CONSTRAINT chave_qrcode_fk
FOREIGN KEY (idChave)
REFERENCES Chave (idChave)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Senha ADD CONSTRAINT chave_senha_fk
FOREIGN KEY (idChave)
REFERENCES Chave (idChave)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Acesso ADD CONSTRAINT chave_acesso_fk
FOREIGN KEY (idChave)
REFERENCES Chave (idChave)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PIN ADD CONSTRAINT senha_pin_fk
FOREIGN KEY (idChave)
REFERENCES Senha (idChave)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;