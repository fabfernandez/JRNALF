insert into subsidiaries (id_subsidiary, sub_name, sub_address, sub_phone, sub_country) values (1, 'Casa Central', 'Sao Paulo 3304', 12344321, 'Brasil');
insert into subsidiaries (id_subsidiary, sub_name, sub_address, sub_phone, sub_country) values (2, 'Casa Buenos Aires', 'Avenida Callao 2000', 23455432, 'Argentina');
insert into subsidiaries (id_subsidiary, sub_name, sub_address, sub_phone, sub_country) values (3, 'Casa Montevideo', '18 de Julio 1023', 34566543, 'Uruguay');
insert into subsidiaries (id_subsidiary, sub_name, sub_address, sub_phone, sub_country) values (4, 'Casa Bogota', 'Medellin 332', 45677654, 'Colombia');
insert into subsidiaries (id_subsidiary, sub_name, sub_address, sub_phone, sub_country) values (5, 'Casa Caracas','Avenida Boyaca 992', 56788765, 'Venezuela');

insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (1, 'Dealer Brasil One', 'Paulo 3304', 12344321, 'Brasil');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (2, 'Dealer Brasil Two', 'Paulo 3304', 12344321, 'Brasil');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (3, 'Dealer Argentina One', 'Callao 2300', 23455432, 'Argentina');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (4, 'Dealer Argentina Two', 'Callao 2500', 23455432, 'Argentina');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (5, 'Dealer Uruguay One', 'Julio 1200', 34566543, 'Uruguay');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (6, 'Dealer Uruguay Two', 'Julio 1400', 34566543, 'Uruguay');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (7, 'Dealer Colombia One', 'Mede 450', 45677654, 'Colombia');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (8, 'Dealer Colombia Two', 'Mede 650', 45677654, 'Colombia');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (9, 'Dealer Venezuela One','Boyaca 1300', 56788765, 'Venezuela');
insert into dealers (id_dealer, dealer_name, dealer_address, dealer_phone, dealer_country) values (10, 'Dealer Venezuela Two','Boyaca 1500', 56788765, 'Venezuela');

-- create parts
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (1, 'Llanta', 'Fiat', 100, 100, 100, 100, 762, 900, '2021-03-02', '2021-03-10', 'AA', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (2, 'Puerta trasera derecha', 'Fiat', 30, 40, 30, 40, 862, 900, '2021-03-02', '2021-04-02', 'AA', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (3, 'Puerta trasera izquierda', 'Fiat', 30, 40, 30, 40, 962, 1000, '2021-01-29', '2021-03-29', 'AA', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (4, 'Puerta delantera derecha', 'Fiat', 30, 40, 30, 40, 2305, 2400, '2020-10-31', NULL, 'AA', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (5, 'Puerta delantera izquierda', 'Fiat', 30, 40, 30, 40, 837, 900, '2020-06-23', NULL, 'BB', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (6, 'Puerta del maletero', 'Fiat', 80, 30, 80, 30, 650, 700, '2020-11-08', '2021-02-13', 'BB', 'D');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (7, 'Foco LED delantero', 'Fiat', 15, 15, 15, 15, 2243, 2300, '2020-06-06', NULL, 'BB', 'D');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (8, 'Espejo retrovisor', 'Fiat', 100, 55, 100, 100, 1236, 1300, '2021-02-13', NULL, 'BB', 'D');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (9, 'Espejo derecho', 'Toyota', 33, 100, 100, 100, 1119, 1200, '2021-02-13', NULL, 'CC', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (10, 'Espejo izquierdo', 'Toyota', 40, 100, 99, 100, 1200, 1300, '2021-02-13', '2021-02-27', 'CC', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (11, 'Caja de cambios', 'Toyota', 40, 33, 55, 100, 358, 400, '2021-02-13', '2021-04-13', 'CC', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (12, 'Asiento delantero', 'Toyota', 55, 40, 100, 92, 2223, 2400, '2021-02-14', '2021-03-14', 'CC', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (13, 'Asiento trasero', 'Toyota', 92, 100, 33, 100, 2200, 2300, '2020-07-22', '2020-08-22', 'CC', 'N');
insert into parts (id_part, description, maker, width_dimension, tall_dimension, long_dimension, net_weight, normal_price, urgent_price, last_modification, last_price_modification, discount_type, part_status) values (14, 'Apoyacabezas', 'Toyota', 92, 33, 100, 92, 2500, 2800, '2021-03-17', '2021-04-25', 'DD', 'N');

-- create dealer_orders
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (1, 7, 5, '2021-04-30', 'P', '2021-05-15', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (2, 2, 5, '2020-09-15', 'D', '2020-09-20', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (3, 3, 1, '2021-04-03', 'F', '2021-05-03', 3);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (4, 6, 4, '2020-07-06', 'C', '2020-08-06', 4);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (5, 10, 4, '2021-01-04', 'F', '2021-03-04', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (6, 7, 5, '2020-06-02', 'P', '2020-07-02', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (7, 1, 4, '2021-03-15', 'C', '2021-05-15', 1);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (8, 8, 3, '2021-03-24', 'D', '2021-03-30', 2);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (9, 3, 3, '2020-11-30', 'F', '2020-12-15', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (10, 7, 5, '2021-04-30', 'P', '2021-05-15', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (11, 2, 5, '2020-09-15', 'D', '2020-09-20', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (12, 3, 1, '2021-05-03', 'F', '2021-06-03', 3);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (13, 6, 4, '2020-07-06', 'C', '2020-08-06', 4);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (14, 10, 4, '2021-01-04', 'F', '2021-03-04', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (15, 7, 5, '2020-06-02', 'P', '2020-07-02', 0);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (16, 1, 4, '2021-03-15', 'C', '2021-05-15', 1);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (17, 8, 3, '2021-03-24', 'D', '2021-03-30', 2);
insert into dealer_orders (id_dealer_order, dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (18, 3, 3, '2020-03-30', 'F', '2020-12-15', 0);


-- create sub_orders
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (1, 5, '2021-04-30', 'P', '2021-05-15', 0);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (2, 5, '2020-09-15', 'D', '2020-09-20', 0);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (3, 1, '2021-04-03', 'F', '2021-05-03', 3);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (4, 4, '2020-07-06', 'C', '2020-08-06', 4);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (5, 4, '2021-01-04', 'F', '2021-03-04', 0);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (6, 5, '2020-06-02', 'P', '2020-07-02', 0);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (7, 4, '2021-03-15', 'C', '2021-05-15', 1);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (8, 3, '2021-03-24', 'D', '2021-03-30', 2);
insert into sub_orders (id_sub_order, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (9, 3, '2020-11-30', 'F', '2020-12-15', 0);

-- create sub_stock
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 1, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 1, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 1, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 1, 0);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 1, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 2, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 2, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 2, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 2, 9);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 2, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 3, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 3, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 3, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 3, 0);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 3, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 4, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 4, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 4, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 4, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 4, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 5, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 5, 8);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 5, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 5, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 5, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 6, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 6, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 6, 9);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 6, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 6, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 7, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 7, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 7, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 7, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 7, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 8, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 8, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 8, 8);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 8, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 8, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 9, 0);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 9, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 9, 0);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 9, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 9, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 10, 9);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 10, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 10, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 10, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 10, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 11, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 11, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 11, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 11, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 11, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 12, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 12, 5);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 12, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 12, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 12, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 13, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 13, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 13, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 13, 3);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 13, 7);
insert into sub_stock (id_subsidiary, part_code, quantity) values (1, 14, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (2, 14, 4);
insert into sub_stock (id_subsidiary, part_code, quantity) values (3, 14, 2);
insert into sub_stock (id_subsidiary, part_code, quantity) values (4, 14, 6);
insert into sub_stock (id_subsidiary, part_code, quantity) values (5, 14, 6);

-- create dealer_orders
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (7, 5, '2021-04-30', 'P', '2021-05-15', 0);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (2, 5, '2020-09-15', 'D', '2020-09-20', 0);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (3, 1, '2021-04-03', 'F', '2021-05-03', 3);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (6, 4, '2020-07-06', 'C', '2020-08-06', 4);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (10, 4, '2021-01-04', 'F', '2021-03-04', 0);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (7, 5, '2020-06-02', 'P', '2020-07-02', 0);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (1, 4, '2021-03-15', 'C', '2021-05-15', 1);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (8, 3, '2021-03-24', 'D', '2021-03-30', 2);
insert into dealer_orders (dealer_id, subsidiary_id, order_date, order_status, delivery_date, days_delay) values (3, 3, '2020-11-30', 'F', '2020-12-15', 0);

-- create dealer_order_items
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (1, 1, 1, 2, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (2, 2, 2, 2, 'R', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (3, 2, 3, 1, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (4, 3, 4, 4, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (5, 3, 5, 2, 'R', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (6, 5, 5, 1, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (7, 6, 6, 3, 'R', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (8, 7, 10, 4, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (9, 8, 10, 5, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (10, 8, 11, 2, 'G', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (11, 9, 10, 3, 'R', 'sin motivo');
insert into dealer_order_items (id_dealer_order_item, order_id, part_id, quantity, account_type, reason) values (12, 18, 9, 10, 'G', 'sin motivo');

-- create sub_order_items
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (1, 1, 4, 2, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (2, 2, 3, 2, 'R', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (3, 3, 1, 1, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (4, 3, 4, 4, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (5, 4, 5, 2, 'R', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (6, 4, 5, 1, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (7, 5, 6, 3, 'R', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (8, 5, 10, 4, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (9, 6, 10, 5, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (10, 8, 11, 2, 'G', 'sin motivo');
insert into sub_order_items (id_sub_order_item, order_id, part_id, quantity, account_type, reason) values (11, 9, 10, 3, 'R', 'sin motivo');

-- create users
insert into users (username, password, id_subsidiary) values ('gembleton0', 'IH4YH7kAk', 1);
insert into users (username, password, id_subsidiary) values ('jspurgeon1', '0xpbqqetW1xC', 2);
insert into users (username, password, id_subsidiary) values ('rtrouncer2', 'RJFdKwtOE', 3);
insert into users (username, password, id_subsidiary) values ('amoxon3', 'A98vXM2llsb', 4);
insert into users (username, password, id_subsidiary) values ('gsyversen4', 'EXMUyGRqefX', 5);