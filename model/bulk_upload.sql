-- Client
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(3, 'person_first_name0', 'person_country_birthday0', 'person_middle_name0', 'person_last_name0', 'person_second_last_name0', 'person_dpi0', '2023-03-04 00:32:25', 'M');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(4, 'person_first_name1', 'person_country_birthday1', 'person_middle_name1', 'person_last_name1', 'person_second_last_name1', 'person_dpi1', '2023-03-05 00:32:25', 'F');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(5, 'person_first_name2', 'person_country_birthday2', 'person_middle_name2', 'person_last_name2', 'person_second_last_name2', 'person_dpi2', '2023-03-06 00:32:25', 'M');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(6, 'person_first_name3', 'person_country_birthday3', 'person_middle_name3', 'person_last_name3', 'person_second_last_name3', 'person_dpi3', '2023-03-07 00:32:25', 'F');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(7, 'person_first_name4', 'person_country_birthday4', 'person_middle_name4', 'person_last_name4', 'person_second_last_name4', 'person_dpi4', '2023-03-08 00:32:25', 'M');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(8, 'person_first_name5', 'person_country_birthday5', 'person_middle_name5', 'person_last_name5', 'person_second_last_name5', 'person_dpi5', '2023-03-09 00:32:25', 'F');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(9, 'person_first_name6', 'person_country_birthday6', 'person_middle_name6', 'person_last_name6', 'person_second_last_name6', 'person_dpi6', '2023-03-10 00:32:25', 'M');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(10, 'person_first_name7', 'person_country_birthday7', 'person_middle_name7', 'person_last_name7', 'person_second_last_name7', 'person_dpi7', '2023-03-11 00:32:25', 'F');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(11, 'person_first_name8', 'person_country_birthday8', 'person_middle_name8', 'person_last_name8', 'person_second_last_name8', 'person_dpi8', '2023-03-12 00:32:25', 'M');
INSERT INTO bank_database.client
(idclient, first_name, country_birthday, middle_name, last_name, second_last_name, dpi, birthday, gender)
VALUES(12, 'person_first_name9', 'person_country_birthday9', 'person_middle_name9', 'person_last_name9', 'person_second_last_name9', 'person_dpi9', '2023-03-13 00:32:25', 'F');

-- Type Product
INSERT INTO bank_database.type_product
(idtype_account, name)
VALUES(1, 'MONETARIO');
INSERT INTO bank_database.type_product
(idtype_account, name)
VALUES(2, 'AHORRO');

-- Product
INSERT INTO bank_database.product
(idproduct, idtype_account, name, interest_calculation, interest_rate)
VALUES(1, 1, 'product_number0', 1, 1);
INSERT INTO bank_database.product
(idproduct, idtype_account, name, interest_calculation, interest_rate)
VALUES(2, 2, 'product_number1', 1, 1);
INSERT INTO bank_database.product
(idproduct, idtype_account, name, interest_calculation, interest_rate)
VALUES(3, 1, 'product_number2', 1, 1);
INSERT INTO bank_database.product
(idproduct, idtype_account, name, interest_calculation, interest_rate)
VALUES(4, 2, 'product_number3', 1, 1);
INSERT INTO bank_database.product
(idproduct, idtype_account, name, interest_calculation, interest_rate)
VALUES(5, 1, 'product_number4', 1, 1);

--Status
INSERT INTO bank_database.status
(idstatus, name)
VALUES(1, 'ACTIVA');
INSERT INTO bank_database.status
(idstatus, name)
VALUES(2, 'INACTIVA');

-- Operation Code
INSERT INTO bank_database.operation_code
(idoperation_code, name, description)
VALUES(1, 'CREDITO', 'CODIGO DE OPERACION CREDITO');
INSERT INTO bank_database.operation_code
(idoperation_code, name, description)
VALUES(2, 'DEBITO', 'CODIGO DE OPERACION DEBITO');

-- Account
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(2, 1, 1, 3, 250.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(3, 2, 2, 5, 150.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(4, 2, 4, 9, 610.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(5, 1, 5, 11, 510.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(6, 1, 3, 12, 400.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(7, 2, 5, 10, 500.00);
INSERT INTO bank_database.account
(idaccount, idstatus, idproduct, idclient, balance)
VALUES(8, 2, 3, 6, 150.00);


-- transaction
INSERT INTO bank_database.`transaction`
(idtransaction, idoperation_code, idaccount, amount, transaction_date, description)
VALUES(2, 1, 2, 100.00, '2023-03-04 06:49:38', 'Description de la transaccion 1');
INSERT INTO bank_database.`transaction`
(idtransaction, idoperation_code, idaccount, amount, transaction_date, description)
VALUES(3, 1, 2, 100.00, '2023-03-04 06:49:39', 'Description de la transaccion 2');
INSERT INTO bank_database.`transaction`
(idtransaction, idoperation_code, idaccount, amount, transaction_date, description)
VALUES(4, 1, 5, 100.00, '2023-03-04 06:49:47', 'Description de la transaccion 3');
INSERT INTO bank_database.`transaction`
(idtransaction, idoperation_code, idaccount, amount, transaction_date, description)
VALUES(5, 2, 5, 100.00, '2023-03-04 06:49:56', 'Description de la transaccion 4');
INSERT INTO bank_database.`transaction`
(idtransaction, idoperation_code, idaccount, amount, transaction_date, description)
VALUES(6, 2, 2, 500.00, '2023-03-04 06:51:22', 'Description de la transaccion 5');
