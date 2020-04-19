DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    id             VARCHAR(36) PRIMARY KEY,
    person_id      VARCHAR(36)    NOT NULL,
    account_number VARCHAR(22)    NOT NULL,
    balance        NUMERIC(10, 2) NOT NULL
);

INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('8e95afad-57ec-4ac9-9de6-dfbb2339323e', '2a21a5ba-ba89-49b0-baa2-f53e2ab83886', 'DE60500105179413579843', 800000.85);
INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('f08b9299-7571-4b7b-b1c4-cbf280e3becc', '2a21a5ba-ba89-49b0-baa2-f53e2ab83886', 'DE19500105176668271643', 2003);
INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('3ffaf01b-aa06-4e80-9028-aca44618a74d', '2a21a5ba-ba89-49b0-baa2-f53e2ab83886', 'DE86500105173371526199', 4055);
INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('a1e5395a-ac84-488b-b267-b67d43673638', 'dd8a3faf-9438-41e0-878e-49d13b86230d', 'DE23500105173917843652', 1000.25);
INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('cf9eeb8c-392f-44f6-a23f-fadb5b231328', 'dd8a3faf-9438-41e0-878e-49d13b86230d', 'DE79500105177628642246', 2000.5);
INSERT INTO accounts (id, person_id, account_number, balance)
VALUES ('7fa97ffc-2d20-4379-8df6-d7bbd99da722', 'f7cfbf56-8150-11ea-bc55-0242ac130003', 'DE40500105178313561112', 1.25);