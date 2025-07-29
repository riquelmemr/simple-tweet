INSERT INTO tb_role (id, name)
SELECT 1, 'ADMIN'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE id = 1);

INSERT INTO tb_role (id, name)
SELECT 2, 'BASIC'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE id = 2);