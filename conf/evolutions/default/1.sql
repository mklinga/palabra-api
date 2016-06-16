# Words schema, Language schema

# --- !Ups

CREATE TABLE Languages (
  id SERIAL PRIMARY KEY,
  code varchar(2) NOT NULL
);

CREATE TABLE WordTypes (
  id SERIAL PRIMARY KEY,
  type varchar(80) NOT NULL
);

CREATE TABLE PersonConjugations (
  id SERIAL PRIMARY KEY,
  person varchar(80) NOT NULL
);

CREATE TABLE TimeConjugations (
  id SERIAL PRIMARY KEY,
  time varchar(80) NOT NULL
);

CREATE TABLE Words (  
    id SERIAL PRIMARY KEY,
    irregular BOOLEAN NOT NULL,
    language_id SERIAL REFERENCES Languages(id) NOT NULL,
    type_id SERIAL REFERENCES WordTypes(id) NOT NULL,
    infinitive VARCHAR(255) NOT NULL
);

CREATE TABLE Conjugations (
  id SERIAL PRIMARY KEY,
  word_id SERIAL REFERENCES Words(id) NOT NULL,
  person_conjugation_id SERIAL REFERENCES PersonConjugations(id) NOT NULL,
  time_conjugation_id SERIAL REFERENCES TimeConjugations(id) NOT NULL,
  value VARCHAR(255) NOT NULL
);

CREATE TABLE RelationTypes (
  id SERIAL PRIMARY KEY,
  relation_type VARCHAR(255)
);

CREATE TABLE WordRelations (
  id SERIAL PRIMARY KEY,
  word_from_id SERIAL REFERENCES Words(id) NOT NULL,
  word_to_id SERIAL REFERENCES Words(id) NOT NULL,
  relation_type_id SERIAL REFERENCES RelationTypes(id) NOT NULL
);

INSERT INTO Languages (id, code) VALUES(1, 'es');
INSERT INTO Languages (id, code) VALUES(2, 'en');

INSERT INTO PersonConjugations (id, person) VALUES(1, '1st');
INSERT INTO PersonConjugations (id, person) VALUES(2, '2nd');
INSERT INTO PersonConjugations (id, person) VALUES(3, '3rd');
INSERT INTO PersonConjugations (id, person) VALUES(4, '1stplural');
INSERT INTO PersonConjugations (id, person) VALUES(5, '2ndplural');
INSERT INTO PersonConjugations (id, person) VALUES(6, '3rdplural');

INSERT INTO TimeConjugations (id, time) VALUES(1, 'present');
INSERT INTO TimeConjugations (id, time) VALUES(2, 'imperfect');

INSERT INTO WordTypes (id, type) VALUES(1, 'verb');
INSERT INTO WordTypes (id, type) VALUES(2, 'noun');

INSERT INTO Words (id, irregular, language_id, type_id, infinitive) Values(1, true, 1, 1, 'tener');

INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 1, 1, 'tengo');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 2, 1, 'tienes');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 3, 1, 'tiene');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 4, 1, 'tenemos');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 5, 1, 'tenéis');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(1, 6, 1, 'tienen');  

INSERT INTO Words (id, irregular, language_id, type_id, infinitive) Values(2, false, 2, 1, 'have');  

INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 1, 1, 'have');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 2, 1, 'have');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 3, 1, 'has');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 4, 1, 'have');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 5, 1, 'have');  
INSERT INTO Conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(2, 6, 1, 'have');  

INSERT INTO RelationTypes (id, relation_type) VALUES (1, 'translation');
INSERT INTO RelationTypes (id, relation_type) VALUES (2, 'synonym');

INSERT INTO WordRelations (id, word_from_id, word_to_id, relation_type_id) VALUES (1, 1, 2, 1);

# --- !Downs

DROP TABLE WordRelations;
DROP TABLE RelationTypes;
DROP TABLE Conjugations;
DROP TABLE Words;
DROP TABLE TimeConjugations;
DROP TABLE PersonConjugations;
DROP TABLE WordTypes;
DROP TABLE Languages;
