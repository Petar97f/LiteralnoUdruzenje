INSERT INTO user (name,surname,email,password, activated, user_role)
VALUES ('User1','Prezime1','user1@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6', true, 0);
INSERT INTO user (name,surname,email,password, activated, user_role)
VALUES ('User2','Prezime2','user2@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6', true, 0);

INSERT INTO user (name,surname,email,password, activated, user_role, username)
VALUES ('User3','Prezime3','user3@gmail.com','$2y$12$jMfmhs1lwpKGSOeLeXkVSu/GOR3vXwG8ICVacerwNFXPKogaf/cq6', true, 3, 'user3');

INSERT INTO genre (name)
VALUES ('horor');

INSERT INTO genre (name)
VALUES ('comedy');