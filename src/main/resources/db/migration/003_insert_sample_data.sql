INSERT INTO customers VALUES (1, 'Ashok Kumar', 'ashok.kumar@gmail.com', 'Ashok Kumar', '9999999998', '1234567890', '{}', 'true');
INSERT INTO customers VALUES (2, 'Rohan Saraf', 'rohan.saraf@gmail.com', 'Rohan Saraf', '9999999997', '1234567891', '{}', 'true');
INSERT INTO customers VALUES (3, 'Varun Mundhra', 'v2mundhra@gmail.com', 'Varun Mundhra', '9999999996', '1234567892', '{}', 'true');
INSERT INTO customers VALUES (4, 'Avi Gupta', 'avi@gmail.com', 'Avi', '9699999996', '1234567893', '{}', 'true');
INSERT INTO customers VALUES (5, 'Love Mehta', 'love@gmail.com', 'Love', '8999999996', '1234567894', '{}', 'true');
INSERT INTO customers VALUES (6, 'Akhil Gupta', 'akhil@gmail.com', 'Akhil Gupta', '7999999996', '1234567895', '{}', 'true');
INSERT INTO customers VALUES (7, 'Chinmaya Verma', 'chinmaya@gmail.com', 'Varun', '6999999996', '1234567896', '{}', 'true');
ALTER SEQUENCE customers_id_seq RESTART With 8;

INSERT INTO bills VALUES (1, 'e21e1Rasd3r1r3r1', 'CREATED', '1234567890', 'DEBIT', 'ONE_TIME', 'EXACT', '900', '900', '{}', now() + '1 day');
INSERT INTO bills VALUES (2, 'e21e1Rasd3r1r3r2', 'CREDIT_RECEIVED', '1234567890', 'DEBIT', 'ONE_TIME', 'EXACT', '8900', '8900', '{}', now() + '1 day');
INSERT INTO bills VALUES (3, 'e21e1Rasd3r1r3r3', 'CREATED', '1234567892', 'DEBIT', 'ONE_TIME', 'EXACT', '890', '890', '{}', now() + '1 day');
INSERT INTO bills VALUES (4, 'e21e1Rasd3r1r3r4', 'SETTLED', '1234567893', 'DEBIT', 'ONE_TIME', 'EXACT', '400', '400','{}', now() + '1 day');
INSERT INTO bills VALUES (5, 'e21e1Rasd3r1r3r5', 'CREDIT_RECEIVED', '1234567890', 'DEBIT', 'ONE_TIME', 'EXACT', '4000', '400','{}', now() + '1 day');
INSERT INTO bills VALUES (6, 'e21e1Rasd3r1r3r6', 'SETTLED', '1234567895', 'DEBIT', 'ONE_TIME', 'EXACT', '400', '450','{}', now() + '1 day');
INSERT INTO bills VALUES (7, 'e21e1Rasd3r1r3r7', 'CREDIT_RECEIVED', '1234567896', 'DEBIT', 'ONE_TIME', 'EXACT', '400', '400','{}', now() + '1 day');
INSERT INTO bills VALUES (9, 'e21e1Rasd3r1r3r8', 'CREATED', '1234567895', 'DEBIT', 'ONE_TIME', 'EXACT', '400', '460','{}', now() + '1 day');
ALTER SEQUENCE bills_id_seq RESTART with 9;