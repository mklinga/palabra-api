# --- !Ups

INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(3, true, 1, 1, 'ser');
INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(5, true, 1, 1, 'estar');
INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(7, true, 1, 1, 'haber');

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 1, 1, 'soy');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 2, 1, 'eres');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 3, 1, 'es');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 4, 1, 'somos');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 5, 1, 'sois');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(3, 6, 1, 'son');  

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 1, 1, 'estoy');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 2, 1, 'estás');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 3, 1, 'está');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 4, 1, 'estamos');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 5, 1, 'estáis');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(5, 6, 1, 'están');  

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 1, 1, 'he');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 2, 1, 'has');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 3, 1, 'ha');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 4, 1, 'hemos');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 5, 1, 'habéis');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(7, 6, 1, 'han');  

INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(4, true, 2, 1, 'to be (permanent)');  
INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(6, true, 2, 1, 'to be (state)');  
INSERT INTO words (id, irregular, language_id, type_id, infinitive) Values(8, false, 2, 1, 'to have (to do something)');  

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 1, 1, 'am');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 2, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 3, 1, 'is');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 4, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 5, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(4, 6, 1, 'are');  

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 1, 1, 'am');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 2, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 3, 1, 'is');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 4, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 5, 1, 'are');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(6, 6, 1, 'are');  

INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 1, 1, 'have');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 2, 1, 'have');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 3, 1, 'has');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 4, 1, 'have');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 5, 1, 'have');  
INSERT INTO conjugations (word_id, person_conjugation_id, time_conjugation_id, value) Values(8, 6, 1, 'have');  

INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (1, 2, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (2, 1, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (3, 4, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (4, 3, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (5, 6, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (6, 5, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (7, 8, 1);
INSERT INTO wordrelations (word_from_id, word_to_id, relation_type_id) VALUES (8, 7, 1);

# --- !Downs
