INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User1','Prezime1','user1@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad1','Country1', true, true, 0,'user1');
INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User2','Prezime2','user2@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad2','Country2', true, true, 0,'user2');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User3','Prezime3','user3@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad3','Country3', true, true, 3,'user3');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User4','Prezime4','user4@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad4','Country1', true, true, 4,'user4');
INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User5','Prezime5','user5@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad5','Country2', true, true, 5,'user5');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('User6','Prezime6','user6@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 6,'user6');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username,points)
VALUES ('UserBetaReader7','Prezime7','betareader1@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 2,'betareader1',0);

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username,points)
VALUES ('UserBetaReader8','Prezime7','betareader2@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 2,'betareader2',0);

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('UserMember7','PrezimeMember7','user7@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 6,'userMember7');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('UserMember8','PrezimeMember8','user8@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 6,'userMember8');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('UserMember9','PrezimeMember9','user9@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad6','Country2', true, true, 6,'userMember9');

INSERT INTO user (name,surname,email,password,city,country, activated,is_beta, user_role,username)
VALUES ('Admin7','Prezime7','admin7@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6','Grad7','Country2', true, true, 7, 'admin7');

INSERT INTO genre (name)
VALUES ('horor');

INSERT INTO genre (name)
VALUES ('comedy');

INSERT INTO user_genres (user_id, genre_id) VALUES (8, 1);
INSERT INTO user_genres (user_id, genre_id) VALUES (3, 2);




INSERT INTO book (id,name,author_id,publisher_id,year_of_issue,num_of_pages,price)
VALUES (1,'Mali princ','1','1','2020-11-11',33,100.00);

INSERT INTO book (id,name,author_id,publisher_id,year_of_issue,num_of_pages,price)
VALUES (2,'Veliki princ','1','1','2020-11-12',71,900.00);
