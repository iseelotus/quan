DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
    id  VARCHAR(36) PRIMARY KEY,
    family_name TEXT NOT NULL,
    given_name TEXT NOT NULL
)

INSERT INTO persons (id, family_name, given_name)
VALUES ('2a21a5ba-ba89-49b0-baa2-f53e2ab83886', 'Peralta', 'Alivia');

INSERT INTO persons (id, family_name, given_name)
VALUES ('dd8a3faf-9438-41e0-878e-49d13b86230d', 'Howard', 'Finnley');

INSERT INTO persons (id, family_name, given_name)
VALUES ('f7cfbf56-8150-11ea-bc55-0242ac130003', 'Sawyer', 'Aayan');
