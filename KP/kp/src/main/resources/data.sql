INSERT INTO merchant (id,error_url,failed_url,merchant_id,password,success_url,bank_id)
VALUES (1,'greska','fail','1','123','uspeh',1);
INSERT INTO merchant (id,error_url,failed_url,merchant_id,password,success_url,bank_id)
VALUES (2,'lose','lose','2','12322','uspeha',2);


INSERT INTO merchant_payment_types(payment_types,merchant_id)
VALUES (0,1);
INSERT INTO merchant_payment_types(payment_types,merchant_id)
VALUES (1,1);
INSERT INTO merchant_payment_types(payment_types,merchant_id)
VALUES (2,1);

INSERT INTO kp.merchant_payment_types (merchant_id,payment_types)
values (2,0);
INSERT INTO kp.merchant_payment_types (merchant_id,payment_types)
values (2,1);
INSERT INTO kp.merchant_payment_types (merchant_id,payment_types)
values (2,2);
