INSERT INTO payment (merchant_id,payment_url,amount)
VALUES ('1','nesto',500);


INSERT INTO card (client_id,card_number,available_money,merchant_id,merchant_password,pan,bank_id,cvc,expiration_date,security_code)
values(1,'123',1000,'1','1234','123',1,'123','10/2022','1234');
INSERT INTO card (client_id,card_number,available_money,merchant_id,merchant_password,pan,bank_id,cvc,expiration_date,security_code)
values(2,'124',500,'2','1234','124',1,'123','10/2021','1234');
