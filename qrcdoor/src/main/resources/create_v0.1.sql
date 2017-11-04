DROP SCHEMA "public" CASCADE;
CREATE SCHEMA "public";

CREATE TABLE "public"."Uf" (
                "idUf" BIGINT NOT NULL,
                "nome" VARCHAR(255) DEFAULT '' NOT NULL,
                "situacao" CHAR(1) DEFAULT 'A' NOT NULL,
                "sigla" CHAR(2) DEFAULT '' NOT NULL,
                CONSTRAINT uf_pk PRIMARY KEY ("idUf")
);
COMMENT ON COLUMN "public"."Uf"."situacao" IS 'A-Ativa,I-Inativa';


CREATE SEQUENCE "public".funcao_idfuncao_seq;

CREATE TABLE "public"."Funcao" (
                "idFuncao" BIGINT NOT NULL DEFAULT nextval('"public".funcao_idfuncao_seq'),
                "descricao" VARCHAR(255) DEFAULT '' NOT NULL,
                "situacao" CHAR(1) DEFAULT 'A' NOT NULL,
                CONSTRAINT funcao_pk PRIMARY KEY ("idFuncao")
);
COMMENT ON COLUMN "public"."Funcao"."situacao" IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE "public".funcao_idfuncao_seq OWNED BY "public"."Funcao"."idFuncao";

CREATE SEQUENCE "public".estrutura_idestrutura_seq;

CREATE TABLE "public"."Estrutura" (
                "idEstrutura" BIGINT NOT NULL DEFAULT nextval('"public".estrutura_idestrutura_seq'),
                "idEstruturaPai" BIGINT NOT NULL,
                "descricao" VARCHAR(255) DEFAULT '' NOT NULL,
                "situacao" CHAR(1) DEFAULT 'A' NOT NULL,
                "tempoMaximoAbertura" INTEGER DEFAULT 60 NOT NULL,
                "foto" BYTEA,
                "fotoExtensao" VARCHAR(16),
                CONSTRAINT estrutura_pk PRIMARY KEY ("idEstrutura")
);
COMMENT ON COLUMN "public"."Estrutura"."situacao" IS 'A-Ativa,I-Inativa';
COMMENT ON COLUMN "public"."Estrutura"."tempoMaximoAbertura" IS 'Tempo máximo entre a abertura e o fechamento. Em segundos.';

CREATE TABLE "public"."Cofre" (
                "idEstrutura" BIGINT NOT NULL,
                CONSTRAINT cofre_pk PRIMARY KEY ("idEstrutura")
);


CREATE TABLE "public"."Ambiente" (
                "idEstrutura" BIGINT NOT NULL,
                "quantidadeMaximaPessoas" INTEGER DEFAULT -1 NOT NULL,
                CONSTRAINT ambiente_pk PRIMARY KEY ("idEstrutura")
);
COMMENT ON COLUMN "public"."Ambiente"."quantidadeMaximaPessoas" IS '-1-Sem controle';


CREATE TABLE "public"."Permissao" (
                "idPermissao" BIGINT NOT NULL,
                "idEstrutura" BIGINT NOT NULL,
                "herda" BOOLEAN DEFAULT false NOT NULL,
                "gerencia" BOOLEAN DEFAULT false NOT NULL,
                "monitora" BOOLEAN DEFAULT false NOT NULL,
                "abre" BOOLEAN DEFAULT false NOT NULL,
                "fecha" BOOLEAN NOT NULL,
                "diasSemana" CHAR(7) DEFAULT '0000000' NOT NULL,
                "meses" CHAR(12) DEFAULT '000000000000' NOT NULL,
                "horaInicio" TIME DEFAULT current_time NOT NULL,
                "horaFim" TIME DEFAULT current_time NOT NULL,
                "dataInicio" DATE DEFAULT current_date NOT NULL,
                "dataFim" DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissao_pk PRIMARY KEY ("idPermissao")
);


CREATE SEQUENCE "public".permissaofuncao_idpermissaofuncao_seq;

CREATE TABLE "public"."PermissaoFuncao" (
                "idPermissaoFuncao" BIGINT NOT NULL DEFAULT nextval('"public".permissaofuncao_idpermissaofuncao_seq'),
                "idPermissao" BIGINT NOT NULL,
                "idFuncao" BIGINT NOT NULL,
                "dataInicio" DATE DEFAULT current_date NOT NULL,
                "dataFim" DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissaofuncao_pk PRIMARY KEY ("idPermissaoFuncao")
);


ALTER SEQUENCE "public".permissaofuncao_idpermissaofuncao_seq OWNED BY "public"."PermissaoFuncao"."idPermissaoFuncao";

CREATE SEQUENCE "public".pessoa_idpessoa_seq;

CREATE TABLE "public"."Pessoa" (
                "idPessoa" BIGINT NOT NULL DEFAULT nextval('"public".pessoa_idpessoa_seq'),
                "nome" VARCHAR(255) DEFAULT '' NOT NULL,
                "situacao" CHAR(1) DEFAULT 'A' NOT NULL,
                "cpf" VARCHAR(32),
                "rg" VARCHAR(32),
                "idUfRg" BIGINT NOT NULL,
                "foto" BYTEA,
                "fotoExtensao" VARCHAR(16),
                CONSTRAINT pessoa_pk PRIMARY KEY ("idPessoa")
);
COMMENT ON COLUMN "public"."Pessoa"."situacao" IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE "public".pessoa_idpessoa_seq OWNED BY "public"."Pessoa"."idPessoa";

CREATE TABLE "public"."FuncaoPessoa" (
                "idFuncaoPessoa" BIGINT NOT NULL,
                "idFuncao" BIGINT NOT NULL,
                "idPessoa" BIGINT NOT NULL,
                "dataInicio" DATE DEFAULT current_date NOT NULL,
                "dataFim" DATE DEFAULT current_date NOT NULL,
                CONSTRAINT funcaopessoa_pk PRIMARY KEY ("idFuncaoPessoa")
);


CREATE SEQUENCE "public".permissaopessoa_idpermissaopessoa_seq;

CREATE TABLE "public"."PermissaoPessoa" (
                "idPermissaoPessoa" BIGINT NOT NULL DEFAULT nextval('"public".permissaopessoa_idpermissaopessoa_seq'),
                "idPermissao" BIGINT NOT NULL,
                "idPessoa" BIGINT NOT NULL,
                "dataInicio" DATE DEFAULT current_date NOT NULL,
                "dataFim" DATE DEFAULT current_date NOT NULL,
                CONSTRAINT permissaopessoa_pk PRIMARY KEY ("idPermissaoPessoa")
);


ALTER SEQUENCE "public".permissaopessoa_idpermissaopessoa_seq OWNED BY "public"."PermissaoPessoa"."idPermissaoPessoa";

CREATE SEQUENCE "public".chave_idchave_seq;

CREATE TABLE "public"."Chave" (
                "idChave" BIGINT NOT NULL DEFAULT nextval('"public".chave_idchave_seq'),
                "idPessoa" BIGINT NOT NULL,
                "descricao" VARCHAR(255) DEFAULT '' NOT NULL,
                "situacao" CHAR(1) DEFAULT 'A' NOT NULL,
                "dataInicio" DATE DEFAULT current_date NOT NULL,
                "dataFim" DATE DEFAULT current_date NOT NULL,
                CONSTRAINT chave_pk PRIMARY KEY ("idChave")
);
COMMENT ON COLUMN "public"."Chave"."situacao" IS 'A-Ativa,I-Inativa';


ALTER SEQUENCE "public".chave_idchave_seq OWNED BY "public"."Chave"."idChave";

CREATE SEQUENCE "public".acesso_idacesso_seq;

CREATE TABLE "public"."Acesso" (
                "idAcesso" BIGINT NOT NULL DEFAULT nextval('"public".acesso_idacesso_seq'),
                "tipo" CHAR(1) NOT NULL,
                "idPessoa" BIGINT NOT NULL,
                "idChave" BIGINT NOT NULL,
                "idEstrutura" BIGINT NOT NULL,
                "idPermissao" BIGINT NOT NULL,
                "dataHoraAbertura" TIMESTAMP DEFAULT current_timestamp,
                "dataHoraFechamento" TIMESTAMP DEFAULT current_timestamp,
                CONSTRAINT acesso_pk PRIMARY KEY ("idAcesso")
);
COMMENT ON COLUMN "public"."Acesso"."tipo" IS 'E-Entrada,S-Saida,A-Abertura';


ALTER SEQUENCE "public".acesso_idacesso_seq OWNED BY "public"."Acesso"."idAcesso";

CREATE TABLE "public"."Senha" (
                "idChave" BIGINT NOT NULL,
                CONSTRAINT senha_pk PRIMARY KEY ("idChave")
);


CREATE TABLE "public"."PIN" (
                "idChave" BIGINT NOT NULL,
                CONSTRAINT pin_pk PRIMARY KEY ("idChave")
);


CREATE TABLE "public"."QRCode" (
                "idChave" BIGINT NOT NULL,
                CONSTRAINT qrcode_pk PRIMARY KEY ("idChave")
);


ALTER TABLE "public"."Pessoa" ADD CONSTRAINT uf_pessoa_fk
FOREIGN KEY ("idUfRg")
REFERENCES "public"."Uf" ("idUf")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."PermissaoFuncao" ADD CONSTRAINT funcao_permissaofuncao_fk
FOREIGN KEY ("idFuncao")
REFERENCES "public"."Funcao" ("idFuncao")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."FuncaoPessoa" ADD CONSTRAINT funcao_funcaopessoa_fk
FOREIGN KEY ("idFuncao")
REFERENCES "public"."Funcao" ("idFuncao")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Permissao" ADD CONSTRAINT estrutura_permissao_fk
FOREIGN KEY ("idEstrutura")
REFERENCES "public"."Estrutura" ("idEstrutura")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Estrutura" ADD CONSTRAINT estrutura_estrutura_fk
FOREIGN KEY ("idEstruturaPai")
REFERENCES "public"."Estrutura" ("idEstrutura")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Acesso" ADD CONSTRAINT estrutura_acesso_fk
FOREIGN KEY ("idEstrutura")
REFERENCES "public"."Estrutura" ("idEstrutura")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Ambiente" ADD CONSTRAINT estrutura_ambiente_fk
FOREIGN KEY ("idEstrutura")
REFERENCES "public"."Estrutura" ("idEstrutura")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Cofre" ADD CONSTRAINT estrutura_cofre_fk
FOREIGN KEY ("idEstrutura")
REFERENCES "public"."Estrutura" ("idEstrutura")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."PermissaoPessoa" ADD CONSTRAINT permissao_permissaopessoa_fk
FOREIGN KEY ("idPermissao")
REFERENCES "public"."Permissao" ("idPermissao")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."PermissaoFuncao" ADD CONSTRAINT permissao_permissaofuncao_fk
FOREIGN KEY ("idPermissao")
REFERENCES "public"."Permissao" ("idPermissao")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Acesso" ADD CONSTRAINT permissao_acesso_fk
FOREIGN KEY ("idPermissao")
REFERENCES "public"."Permissao" ("idPermissao")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Chave" ADD CONSTRAINT pessoa_chave_fk
FOREIGN KEY ("idPessoa")
REFERENCES "public"."Pessoa" ("idPessoa")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."PermissaoPessoa" ADD CONSTRAINT pessoa_permissaopessoa_fk
FOREIGN KEY ("idPessoa")
REFERENCES "public"."Pessoa" ("idPessoa")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Acesso" ADD CONSTRAINT pessoa_acesso_fk
FOREIGN KEY ("idPessoa")
REFERENCES "public"."Pessoa" ("idPessoa")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."FuncaoPessoa" ADD CONSTRAINT pessoa_funcaopessoa_fk
FOREIGN KEY ("idPessoa")
REFERENCES "public"."Pessoa" ("idPessoa")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."QRCode" ADD CONSTRAINT chave_qrcode_fk
FOREIGN KEY ("idChave")
REFERENCES "public"."Chave" ("idChave")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Senha" ADD CONSTRAINT chave_senha_fk
FOREIGN KEY ("idChave")
REFERENCES "public"."Chave" ("idChave")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."Acesso" ADD CONSTRAINT chave_acesso_fk
FOREIGN KEY ("idChave")
REFERENCES "public"."Chave" ("idChave")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "public"."PIN" ADD CONSTRAINT senha_pin_fk
FOREIGN KEY ("idChave")
REFERENCES "public"."Senha" ("idChave")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;






insert into pessoa
values (0,'064.635.199-01','gio.beltrame@gmail.com','F',null,'jpg','admin','Giovanni','ADMINISTRADOR','$2a$10$hPQBWLPxsXDQhX4VCYjYfumFgDkCTlRgdf/k/LJ4ZEJT7Ny2Ag.yi','A','996724004','30301040');