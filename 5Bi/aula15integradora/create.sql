CREATE TABLE IF NOT EXISTS endereco (
id int auto_increment primary key,
rua varchar (36),
numero varchar (12),
cidade varchar (36),
bairro varchar (36)
);

CREATE TABLE IF NOT EXISTS paciente (
id int auto_increment primary key,
nome varchar (36),
sobrenome varchar (36),
rg varchar (36),
data_nascimento DATE (36),
endereco_id int
);