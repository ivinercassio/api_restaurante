-- Script de dados iniciais para MySQL
-- Permissões
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('CARDAPIO_GET', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('CARDAPIO_POST', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('CARDAPIO_PUT', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('CARDAPIO_DELETE', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITA_GET', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITA_POST', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITA_PUT', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITA_DELETE', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('INGREDIENTE_GET', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('INGREDIENTE_POST', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('INGREDIENTE_PUT', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('INGREDIENTE_DELETE', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITAINGREDIENTE_GET', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITAINGREDIENTE_POST', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('RECEITAINGREDIENTE_DELETE', NOW(), NOW());
