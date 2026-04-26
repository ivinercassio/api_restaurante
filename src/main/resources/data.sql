-- Script de dados iniciais para MySQL
-- Permissao
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

INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('USUARIO_GET', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('USUARIO_POST', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('USUARIO_PUT', NOW(), NOW());
INSERT INTO permissao (nome, dt_criacao, dt_alteracao) VALUES ('USUARIO_DELETE', NOW(), NOW());

-- Perfil
INSERT INTO perfil (nome, dt_criacao, dt_alteracao) VALUES ('ADMIN', NOW(), NOW());
INSERT INTO perfil (nome, dt_criacao, dt_alteracao) VALUES ('COZINHEIRO', NOW(), NOW());

-- Perfil_Permissoes (tabela relacional)
INSERT INTO perfis_permissoes (perfil_id, permissao_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15);

INSERT INTO perfis_permissoes (perfil_id, permissao_id) VALUES (2, 1), (1, 5), (1, 6), (1, 7), (1, 9), (1, 10), (1, 11), (1, 13), (1, 14);
