-- Para rodar no sql server precisa trocar o tipo da seção
ALTER  SESSION SET "_ORACLE_SCRIPT"=true;
-- Comando para criar o usuário expert
CREATE USER expert IDENTIFIED BY expert;

-- Forence permissão ao usuário
GRANT CREATE SESSION TO expert;
GRANT USERS TO expert;
GRANT CREATE ANY TABLE TO expert;
GRANT CREATE TABLE TO expert;
GRANT resource TO expert;
GRANT CREATE PROCEDURE TO expert;
GRANT CREATE VIEW TO expert;
GRANT DROP ANY TABLE TO expert ;
GRANT SELECT ANY TABLE TO expert ;
GRANT UPDATE ANY TABLE TO expert ;
GRANT INSERT ANY TABLE TO expert ;
GRANT CREATE ANY directory TO expert
ALTER USER expert quota unlimited on USERS;

-- Criando o diretório
Create OR Replace Directory excel_files as  '/ORCL/excel';

-- CRIA TABELA QUE ARMAZENA O BLOB
CREATE TABLE my_blob_table(
    file_name VARCHAR2(50) NOT NULL,
    blob_column BLOB NOT NULL,
    PRIMARY KEY(file_name)
);

-- PROCEDURE PARA CRIAÇÃO DO RELATÓRIO
CREATE OR REPLACE PROCEDURE GEN_CUSTOMER_XLSX (p_file_name VARCHAR2) IS
    b_blob blob;
BEGIN
    xlsx_builder_pkg.clear_workbook;
    xlsx_builder_pkg.new_sheet ('pedidos');
    xlsx_builder_pkg.query2sheet (p_sql =>
'SELECT
    coc.email_address AS "Endereço de Email",
    coo.order_datetime AS "Data do Pedido",
    cop.product_name AS "Produto",
    coo.order_id AS "Número do Pedido",
    coo.order_status AS "Estado do pedido",
    coi.quantity AS "Quatidade",
    coi.unit_price AS "Preço"
FROM co_customers coc
    JOIN co_orders coo ON coc.customer_id = coo.customer_id
    JOIN co_order_items coi ON coi.order_id = coo.order_id
    JOIN co_products cop ON cop.product_id = coi.product_id', p_sheet => 1);
    xlsx_builder_pkg.new_sheet ('valor_gasto');
    xlsx_builder_pkg.query2sheet (p_sql =>
'SELECT
    ORDER_ID AS "Número do Pedido",
    CUSTOMER_ID AS "Código do Cliente",
    SUM(QUANTITY) AS "Quantidade de Itens",
    SUM(TOTAL_ORDER) AS "Total do Pedido"
FROM (SELECT
    coo.customer_id,
    coo.order_datetime,
    coo.order_id,
    coo.order_status,
    coi.quantity,
    coi.unit_price,
    coi.quantity * coi.unit_price AS total_order
FROM co_orders coo
    JOIN co_order_items coi ON coi.order_id = coo.order_id) GROUP BY ORDER_ID, CUSTOMER_ID'
, p_sheet => 2);
    xlsx_builder_pkg.save ('EXCEL_FILES', p_file_name||'.xlsx');
    b_blob := xlsx_builder_pkg.finish;
    INSERT INTO my_blob_table (file_name, blob_column) values (p_file_name||'.xlsx', b_blob);
    Commit;
END;

-- EXECUTA A PROCEDURE
EXEC GEN_CUSTOMER_XLSX('TESTE2');