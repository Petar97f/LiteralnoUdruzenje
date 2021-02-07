INSERT INTO merchant (id,error_url,failed_url,merchant_id,password,success_url,bank_id)
VALUES (1,'http://localhost:3005/error','http://localhost:3005/failed','1','123','http://localhost:3005/success',1);
INSERT INTO merchant (id,error_url,failed_url,merchant_id,password,success_url,bank_id)
VALUES (2,'http://localhost:3005/error','http://localhost:3005/failed','3','1234','http://localhost:3005/success',2);


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
