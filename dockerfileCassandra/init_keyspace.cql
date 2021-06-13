-- Here, you can execute any CQL commands, e.g. 
-- dentro de contenedor de cassandra ejecutar 
--nodetool status 
-- docker exec -it cassandra bash
-- docker build -t camiloriguer/cassandra-meli .
-- docker run -d -p  9042:9042 --name=cassandra camiloriguer/cassandra-meli
-- docker push camiloriguer/cassandra-meli


CREATE KEYSPACE meli_keyspace WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE meli_keyspace;

CREATE TABLE meli_keyspace.Adn (
    id uuid PRIMARY KEY,
    dna list<text>,
    response boolean,
    date timestamp
);

CREATE INDEX adn_response ON meli_keyspace.adn (response);
