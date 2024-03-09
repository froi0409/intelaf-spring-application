use intelafdb;


insert into `intelafdb`.`store` (id_store, name, address, phone1, phone2, email) values ('STR-1', 'First Trust Capital Strength ETF', '17256 Old Shore Point', 9092674, 29787227, 'nroony0@google.pl');
insert into `intelafdb`.`store`(id_store, name, address, phone1, phone2, email) values ('STR-2', 'Shopify Inc.', '517 Pankratz Plaza', 16898260, 33287964, 'bower1@hexun.com');
insert into `intelafdb`.`store` (id_store, name, address, phone1, phone2, email) values ('STR-3', 'Avenue Income Credit Strategies Fund', '5 Granby Drive', 24565187, 26269204, 'hwinsborrow2@fc2.com');
insert into `intelafdb`.`store` (id_store, name, address, phone1, phone2, email) values ('STR-4', 'Cadence Design Systems, Inc.', '5297 Brentwood Crossing', 26445604, 24758118, 'hkomorowski3@163.com');
insert into `intelafdb`.`store` (id_store, name, address, phone1, phone2, email) values ('STR-5', 'First United Corporation', '8415 Dunning Plaza', 20808495, 9813437, 'aholberry4@xrea.com');
insert into `intelafdb`.`store` (id_store, name, address, phone1, phone2, email) values ('STR-6', 'MaxPoint Interactive, Inc.', '865 Morning Trail', 20330782, 24351225, 'jgommes5@boston.com');


insert into `intelafdb`.`shipping_time` (`id_store1`, `id_store2`, `time`) values ('STR-1', 'STR-2', 2);
insert into `intelafdb`.`shipping_time` (`id_store1`, `id_store2`, `time`) values ('STR-1', 'STR-3', 3);
insert into `intelafdb`.`shipping_time` (`id_store1`, `id_store2`, `time`) values ('STR-1', 'STR-4', 1);
insert into `intelafdb`.`shipping_time` (`id_store1`, `id_store2`, `time`) values ('STR-2', 'STR-5', 4);
insert into `intelafdb`.`shipping_time` (`id_store1`, `id_store2`, `time`) values ('STR-2', 'STR-6', 3);

insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-1', 'Product 1', 'Manufacturer A', 50.00, 'Description for Product 1', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-2', 'Product 2', 'Manufacturer B', 10.00, 'Description for Product 2', 6);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-3', 'Product 3', 'Manufacturer C', 11.00, 'Description for Product 3', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-4', 'Product 4', 'Manufacturer D', 25.00, 'Description for Product 4', 8);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-5', 'Product 5', 'Manufacturer A', 12.00, 'Description for Product 5', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-6', 'Product 6', 'Manufacturer B', 31.00, 'Description for Product 6', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-7', 'Product 7', 'Manufacturer C', 33.00, 'Description for Product 7', 1);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-8', 'Product 8', 'Manufacturer D', 10.00, 'Description for Product 8', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-9', 'Product 9', 'Manufacturer A', 51.00, 'Description for Product 9', 12);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-10', 'Product 10', 'Manufacturer B', 87.00, 'Description for Product 10', 6);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-11', 'Product 11', 'Manufacturer C', 78.00, 'Description for Product 11', 7);
insert into `intelafdb`.`product` (`id_product`, `name`, `manufacturer`, `price`, `description`, `guaranty_months`) values ('PR-12', 'Product 12', 'Manufacturer D', 57.00, 'Description for Product 12', 5);


insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('123456789', 'Juan', '12345678', '1234567890123', 'user1@example.com', '543 Plaza Trail', 'password1', 'user1');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('987654321', 'Pedro', '87654321', '8745412325415', 'user2@example.com', '25 Morning Plaza', 'password2', 'user2');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('152478455', 'Maria', '33354125', '9876543210987', 'user3@example.com', '865 Morning Trail', 'password3', 'user3');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('145785694', 'Robert', '35687458', '7854785412565', 'user4@example.com', '865 Delfino Plaza', 'password4', 'user4');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('124515784', 'Jhon', '36587458', '5214523658745', 'user5@example.com', '87 Star Trail', 'password5', 'user5');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('568745898', 'Michael', '36598758', '4526587458745', 'user6@example.com', '14 Morning Trail', 'password6', 'user6');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('587458965', 'David', '36589658', '7845962352415', 'user7@example.com', '78 Crossing Streets', 'password7', 'user7');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('457845214', 'Richard', '36987589', '7485859632415', 'user8@example.com', '94 Gatsby Drive', 'password8', 'user8');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('458685745', 'Daniel', '36587589', '1452454587485', 'user9@example.com', '865 Morning Trail', 'password9', 'user9');
insert into `intelafdb`.`user` (`nit`, `name`, `phone`, `dpi`, `email`, `address`, `password`, `username`) values ('142574586', 'Mateo', '35685365', '1452638596745', 'user10@example.com', '865 Morning Trail', 'password10', 'user10');

insert into `intelafdb`.`employee` (`id_user`, `role`) values (1, 'admin');
insert into `intelafdb`.`employee` (`id_user`, `role`) values (2, 'employee');
insert into `intelafdb`.`employee` (`id_user`, `role`) values (3, 'employee');
insert into `intelafdb`.`employee` (`id_user`, `role`) values (4, 'employee');

insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (5, 500.00);
insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (6, 0);
insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (7, 10.00);
insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (8, 14.00);
insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (9, 71.00);
insert into `intelafdb`.`customer` (`user_id_user`, `credit`) values (10, 0);

INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-1', 'PR-1', 50);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-2', 'PR-1', 1);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-3', 'PR-1', 32);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-4', 'PR-2', 14);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-5', 'PR-3', 12);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-6', 'PR-4', 41);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-1', 'PR-5', 8);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-2', 'PR-6', 4);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-3', 'PR-6', 45);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-3', 'PR-7', 12);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-2', 'PR-8', 14);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-1', 'PR-9', 44);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-1', 'PR-10', 14);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-3', 'PR-11', 7);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-5', 'PR-11', 84);
INSERT INTO `intelafdb`.`store_has_product` (`store_id_store`, `product_id_product`, `stock`) values ('STR-6', 'PR-12', 62);

insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('101','2024-03-05 12:00:00', 150.00, 5);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('102','2024-03-06 14:30:00', 200.00, 6);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('103','2024-03-06 14:35:00', 50.00, 7);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('104','2024-03-07 10:45:00', 20.00, 9);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('105','2024-03-07 12:45:00', 30.00, 6);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('106','2024-03-07 13:30:00', 150.00, 7);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('107','2024-03-08 15:45:00', 170.00, 8);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('108','2024-03-08 16:12:00', 10.00, 9);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('109','2024-03-08 17:23:00', 50.00, 5);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('110','2024-03-09 12:54:00', 150.00, 9);
insert into `intelafdb`.`sale` (`id_sale`, `date`, `total`, `id_customer`) values ('111','2024-03-10 13:34:00', 20.00, 10);

insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (101, 'cash', 150.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (102, 'cash', 100.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (102, 'credit', 100.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (103, 'cash', 50.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (104, 'cash', 20.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (105, 'cash', 25.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (105, 'credit', 5.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (106, 'cash', 150.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (107, 'cash', 170.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (108, 'cash', 10.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (109, 'cash', 50.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (110, 'cash', 100.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (110, 'credit', 50.00);
insert into `intelafdb`.`payment_sale` (`sale_id_sale`, `type`, `amount`) values (111, 'cash', 20.00);

insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (101, 'PR-1', 3);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (102, 'PR-1', 4);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (103, 'PR-1', 2);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (104, 'PR-2', 2);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (105, 'PR-2', 3);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (106, 'PR-1', 3);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (107, 'PR-2', 2);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (107, 'PR-1', 3);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (108, 'PR-2', 1);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (109, 'PR-2', 5);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (110, 'PR-1', 2);
insert into `intelafdb`.`sale_has_product` (`sale_id_sale`, `product_id_product`, `quantity`) values (111, 'PR-2', 2);

insert into `intelafdb`.`store_has_employee` (`store_id_store`, `employee_id_user`) values ('STR-1', 1);
insert into `intelafdb`.`store_has_employee` (`store_id_store`, `employee_id_user`) values ('STR-2', 2);
insert into `intelafdb`.`store_has_employee` (`store_id_store`, `employee_id_user`) values ('STR-3', 3);
insert into `intelafdb`.`store_has_employee` (`store_id_store`, `employee_id_user`) values ('STR-4', 4);


insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (100, 'STR-1', 'STR-2', '2024-03-05 10:00:00', '2024-03-07 15:30:00', 150.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (101, 'STR-1', 'STR-2', '2024-03-05 10:30:00', '2024-03-07 10:30:00', 20.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (102, 'STR-1', 'STR-2', '2024-03-05 10:35:00', '2024-03-07 10:30:00', 10.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (103, 'STR-1', 'STR-3', '2024-03-05 10:00:00', '2024-03-08 10:30:00', 100.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (104, 'STR-1', 'STR-3', '2024-03-05 10:30:00', '2024-03-08 10:30:00', 10.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (105, 'STR-2', 'STR-5', '2024-03-05 10:00:00', '2024-03-07 10:30:00', 10.00, 'Route');
insert into `intelafdb`.`order` (`id_order`, `id_store_shipping`, `id_store_receive`, `date_departure`, `date_entry`, `total`, `status`) values (106, 'STR-2', 'STR-5', '2024-03-05 10:30:00', '2024-03-07 10:30:00', 10.00, 'Route');

insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (100, 'PR-1', 3);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (101, 'PR-2', 2);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (102, 'PR-2', 1);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (103, 'PR-1', 2);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (104, 'PR-2', 1);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (105, 'PR-2', 1);
insert into `intelafdb`.`order_has_product` (`order_id_order`, `product_id_product`, `quantity`) values (106, 'PR-2', 1);

insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (1, 100, 'cash', 150.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (2, 101, 'cash', 20.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (3, 102, 'cash', 10.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (4, 103, 'cash', 100.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (5, 104, 'cash', 10.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (6, 105, 'cash', 10.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (7, 106, 'cash', 5.00);
insert into `intelafdb`.`payment_order` (`id_payment_order`, `order_id_order`, `type`, `amount`) values (8, 106, 'credit', 5.00);









