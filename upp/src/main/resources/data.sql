INSERT INTO user (name,surname,email,password, activated, user_role)
VALUES ('User1','Prezime1','user1@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6', true, 0);
INSERT INTO user (name,surname,email,password, activated, user_role)
VALUES ('User2','Prezime2','user2@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6', true, 0);

INSERT INTO genre (name)
VALUES ('horor');

INSERT INTO genre (name)
VALUES ('comedy');

INSERT INTO book (id,name,authorId,publisherId,genre,yearOfIssue,editors,lectors,numOfPages,price)
VALUES (1,'Mali princ','1','1','knjiga','2020-11-11','nema','nema',33,100.00);

INSERT INTO book (id,name,authorId,publisherId,genre,yearOfIssue,editors,lectors,numOfPages,price)
VALUES (2,'Veliki princ','1','1','kopija','2020-11-12','nema','nema',71,900.00);
