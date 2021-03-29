CREATE SCHEMA telefonia;

CREATE TABLE telefonia.Telefone (
	id integer auto_increment NOT NULL,
	codigo_internacional VARCHAR(2) NOT NULL,
	ddd VARCHAR(2) NOT NULL,
	numero VARCHAR(9) NOT NULL,
	id_cliente integer NULL, --atributo da tabela cliente, mas não é FK!
	movel BOOL NULL,
	ativo BOOL NULL,
	CONSTRAINT telefone_pk PRIMARY KEY (id)
);

CREATE TABLE telefonia.Endereco (
	id integer auto_increment NULL,
	cep VARCHAR(8) NOT NULL,
	logradouro VARCHAR(500) NOT NULL,
	cidade VARCHAR(100) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	CONSTRAINT endereco_pk PRIMARY KEY (id)
);

CREATE TABLE telefonia.Cliente (
	id integer auto_increment NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	ativo BOOL NULL,
	id_endereco integer NOT NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (id),
	CONSTRAINT cliente_fk FOREIGN KEY (id_endereco) REFERENCES telefonia.Endereco(id)
);

