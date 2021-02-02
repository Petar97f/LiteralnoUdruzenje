INSERT INTO payment (merchant_id,payment_url,amount)
VALUES ('1','nesto',500);


INSERT INTO card (client_id,card_number,available_money,merchant_id,pan,bank_id,cvc,expiration_date,merchant_password,security_code)
values(1,'222',1000,'1','222',2,'111','11/2020','1234','1234');
INSERT INTO card (client_id,card_number,available_money,merchant_id,pan,bank_id,cvc,expiration_date,merchant_password,security_code)
values(2,'12345',500,'2','12345',2,'111','11/2020','1234','1234');
