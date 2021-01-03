INSERT INTO payment (merchant_id,payment_url,amount)
VALUES ('1','nesto',500);



INSERT INTO bank (address,name)
VALUES ('a1','name1');
INSERT INTO bank (address,name)
VALUES ('a2','name2');
INSERT INTO card (client_id,card_number,available_money,merchant_id,pan,bank_id)
values(1,'123',1000,'1','123',1);
INSERT INTO card (client_id,card_number,available_money,merchant_id,pan,bank_id)
values(2,'1234',500,'2','1234',2);
