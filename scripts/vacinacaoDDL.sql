CREATE SCHEMA vacinacao;
-- Alteração teste alison
CREATE TABLE vacinacao.Pessoa (
	id integer auto_increment NOT NULL,
	nome VARCHAR(200) NOT NULL,
	sexo VARCHAR(1) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	tipo NUMERIC NOT NULL,
	CONSTRAINT pessoa_pk PRIMARY KEY (id)
);

CREATE TABLE vacinacao.Vacina (
	id integer auto_increment NOT NULL,
	nome VARCHAR(200) NOT NULL,
	pais_origem VARCHAR(200) NOT NULL,
	estagio_pesquisa VARCHAR(200) NOT NULL,
	data_inicio_pesquisa DATE NOT NULL,
	fase NUMERIC NOT NULL,
	quantidade_doses NUMERIC NOT NULL,
	id_pessoa_responsavel integer NOT NULL, 
	CONSTRAINT vacina_pk PRIMARY KEY (id),
	CONSTRAINT vacina_fk_1 FOREIGN KEY (id) REFERENCES vacinacao.Pessoa(id)
);

CREATE TABLE vacinacao.AplicacaoVacina (
	id integer auto_increment NULL,
	id_vacina integer NOT NULL, 
	id_pessoa integer NOT NULL, 
	CONSTRAINT aplicacao_fk_1 FOREIGN KEY (id_vacina) REFERENCES vacinacao.Vacina(id),
	CONSTRAINT aplicacao_fk_2 FOREIGN KEY (id_pessoa) REFERENCES vacinacao.Pessoa(id)
);
