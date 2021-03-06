CREATE SCHEMA DBREVENDAVEICULOS; -- alterado no branch vilmar

CREATE TABLE DBREVENDAVEICULOS.CLIENTE (
IDCLIENTE INT NOT NULL PRIMARY KEY AUTO_INCREMENT
, NOME VARCHAR(255)
, CPF CHAR(11)
, TELEFONE VARCHAR(255)
);

CREATE TABLE DBREVENDAVEICULOS.VEICULO (
IDVEICULO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
, MODELO VARCHAR(255)
, TIPO ENUM('CARRO','MOTO','SUV')
, FABRICANTE VARCHAR(255)
, ANO INTEGER(4)
, COR VARCHAR(255)
, PLACA VARCHAR(255)
);

CREATE TABLE DBREVENDAVEICULOS.VENDA (
IDVENDA INT NOT NULL AUTO_INCREMENT
, IDCLIENTE INT
, IDVEICULO INT
, VALORVENDA DECIMAL(10,2)
, DATAVENDA DATE
, PRIMARY KEY (IDVENDA)
, CONSTRAINT FK_VENDA_CLIENTE FOREIGN KEY (IDCLIENTE) REFERENCES DBREVENDAVEICULOS.CLIENTE(IDCLIENTE)
, CONSTRAINT FK_VENDA_VENDA FOREIGN KEY (IDVEICULO) REFERENCES DBREVENDAVEICULOS.VEICULO(IDVEICULO)
);