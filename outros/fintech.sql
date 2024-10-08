
create table T_CONTA (
    cd_conta number(15) generated always as identity (start with 1 increment by 1) not null,
    nr_saldo number(15) not null,
    id_email varchar2(100),
    dt_abertura date not null,
    st_conta varchar2(10) not null,
    tp_conta varchar2(10) not null,
    constraint PK_conta_cd_conta Primary Key(cd_conta),
    constraint UN_conta_id_emaill Unique(id_email)
);


create table T_USUARIO (
    id_usuario number (15),
    cd_conta number(9),
    nm_usuario varchar(50),
    id_cpf char(11),
    id_cnpj char(14),
    dt_nasc date,
    dt_abertura_empresa date,
    constraint PK_usuario_id_usuario Primary Key(id_usuario),
    constraint FK_usuario_cd_conta Foreign Key(cd_conta) References T_CONTA(cd_conta),
    constraint UN_usuario_id_cpf Unique(id_cpf),
    constraint UN_usuario_id_cnpj Unique(id_cnpj)
);

create table T_ENDERECO (
    id_endereco number(15),
    id_usuario number(15),
    id_cep char(8),
    nm_cidade varchar2(40),
    nm_estado varchar2(20),
    nm_bairro varchar2(20),
    nm_logradouro varchar2(50),
    nr_residencia number(10),
    constraint PK_endereco_id_endereco Primary Key(id_endereco),
    constraint FK_endereco_id_usuario Foreign Key(id_usuario) References T_USUARIO(id_usuario)
); 

create table T_TRANSFERENCIAS (
    id_transferencia number(15),
    cd_conta number(8),
    tp_transferencia varchar2(20),
    dt_transferencia date,
    val_transferencia number(10,2),
    constraint PK_transferencias_id_transferencia Primary Key(id_transferencia),
    constraint FK_tranferencias_cd_conta Foreign Key(cd_conta) References T_CONTA(cd_conta)
);

create table T_CARTAO (
    id_cartao number(15),
    cd_conta number (8),
    nr_cartao varchar2 (16),
    nm_bandeira varchar2 (20),
    dt_vencimento date,
    nr_cvv char (3),
    constraint PK_cartao_id_cartao Primary Key(id_cartao),
    constraint UN_cartao_nr_cartao Unique(nr_cartao),
    constraint FK_cartao_cd_conta Foreign Key(cd_conta) References T_CONTA(cd_conta)
);


create table T_FATURA (
    id_fatura number(15),
    id_cartao number(6),
    val_fatura number(10,2),
    dt_vencimento_fatura date,
    dt_pagamento_fatura date,
    constraint PK_fatura_id_transferencia Primary Key(id_fatura),
    constraint FK_fatura_id_cartao Foreign Key(id_cartao) References T_CARTAO(id_cartao)
);


create table T_TELEFONE (
    nr_telefone char(9),
    nr_ddd char(2),
    id_usuario number(8),
    tp_telefone varchar2(15),
    constraint PK_telefone_nr_telefone Primary Key(nr_telefone, nr_ddd),
    constraint FK_telefone_id_usuario Foreign Key(id_usuario) References T_USUARIO(id_usuario)
);

create table T_INVESTIMENTO (
    id_investimento number(15),
    cd_conta number(8),
    tp_investimento varchar2(20),
    dt_investimento date,
    val_investido number(10,2),
    val_rendimento number(10,2),
    constraint PK_investimento_id_investimento Primary Key(id_investimento),
    constraint FK_investimento_cd_conta Foreign Key(cd_conta) References T_CONTA(cd_conta)
);


-- 
-- 


COMANDOS DE CADASTRO:

-- Cadastrar os dados para a conta do usuário.
INSERT INTO T_CONTA (cd_conta, nr_saldo, id_email, dt_abertura, st_conta, tp_conta)
VALUES (1, 10.00, 'testedoemail@gmail.com', to_date('02/jun/1995', 'dd/mm/yyyy'), 'ativo', 'física');

-- Cadastrar os dados do usuário.
INSERT INTO T_USUARIO (id_usuario, cd_conta, nm_usuario, id_cpf, id_cnpj, dt_nascimento, dt_abertura_empresa)
VALUES (1, 1, 'João Santos', '11133377735', null, to_date('03/jun/1995', 'dd/mm/yyyy'), to_date('04/jun/1995', 'dd/mm/yyyy'));


-- Cadastrar as receitas do usuário.
INSERT INTO T_TRANSFERENCIAS (id_transferencia, cd_conta, tp_transferencia, dt_transferencia, val_transferencia)
VALUES (1, 1, 'receita', to_date('05/jun/1995', 'dd/mm/yyyy'), 135.14);

--Cadastrar as despesas do usuário.
INSERT INTO T_TRANSFERENCIAS (id_transferencia, cd_conta, tp_transferencia, dt_transferencia, val_transferencia)
VALUES (2, 1, 'despesa', to_date('06/jun/1995', 'dd/mm/yyyy'), 94.04);

-- Cadastrar os dados para investimentos.
INSERT INTO T_INVESTIMENTO (id_investimento, cd_conta, tp_investimento, dt_investimento, val_investido, val_rendimento)
VALUES (1, 1, 'CDB', to_date('10/jun/1995', 'dd/mm/yyyy'), 13000.00, 1000.50);


-- COMANDOS PARA ALTERAÇÃO DE DADOS:

-- Alterar todos os dados do usuário, utilizando seu código como referência.
UPDATE T_USUARIO
SET nm_usuario = 'Mario Silva', id_cpf = 11122233345, id_cnpj = null, dt_nasc = to_date('13/jun/1995', 'dd/mm/yyyy'), dt_abertura_empresa = to_date('20/jun/1995', 'dd/mm/yyyy')
WHERE id_usuario = &id_usuario;

-- Alterar todos os dados das receitas do usuário, utilizando o código como referência.
UPDATE T_TRANSFERENCIAS
SET tp_transferencia = 'receita', dt_transferencia = to_date('04/05/1995', 'dd/mm/yyyy'), val_transferencia = 145.58?
WHERE id_transferencia = &id_transferencia;

-- Alterar todos os dados das despesas do usuário, utilizando o código como referência.
UPDATE T_TRANSFERENCIAS
SET tp_transferencia = 'despesa', dt_transferencia = to_date('04/jan/1995', 'dd/mm/yyyy'), val_transferencia = 124.04?
WHERE id_transferencia = &id_transferencia;

-- Alterar todos os dados para investimentos do usuário, utilizando o código como referência.
UPDATE T_INVESTIMENTO
SET tp_investimento = 'LCA', dt_investimento = to_date('04/feb/1995', 'dd/mm/yyyy'), val_investido = 230.00, val_rendimento = 56.60
WHERE id_investimento = &id_investimento;

COMANDO PARA CONSULTAS

--Consultar os dados de um usuário 
select * from T_USUARIO where id_usuario = &id_usuario; 

-- Consultar os dados de um único registro de despesa de um  usuário (filtrar a partir do código do usuário e do código da despesa).
select * from T_TRANSFERENCIAS where cd_conta = &cd_conda and id_transferencia = &id_transferencia;

--Consultar os dados de todos os registros de despesas de um  usuário, ordenando-os dos registros mais recentes para os mais antigos
select * from T_TRANSFERENCIAS where tp_transferencia = 'despesa' and cd_conta = &cd_conta order by dt_transferencia desc;

--Consultar os dados de um único registro de investimento de um  usuário (filtrar a partir do código do usuário e do código de investimento).
select * from T_INVESTIMENTO
where cd_conta = &cd_conda and id_investimento = &id_investimento;

--Consultar os dados de todos os registros de investimentos de um  usuário, ordenando-os dos registros mais recentes para os mais antigos 
select * from t_investimento where cd_conta = &cd_conta order by dt_investimento desc;

--Consultar os dados básicos de um usuário, o último investimento registrado e a última despesa registrada
SELECT U.id_usuario, U.nm_usuario, I.*, F.*
FROM T_USUARIO U
LEFT JOIN T_INVESTIMENTO I ON U.cd_conta = I.cd_conta
LEFT JOIN T_TRANSFERENCIA F ON U.cd_conta = F.cd_conta
WHERE U.id_usuario = &id_usuario
ORDER BY I.dt_investimento DESC, F.dt_transferencia DESC
FETCH FIRST 1 ROWS ONLY;
