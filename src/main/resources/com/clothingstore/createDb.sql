CREATE TABLE staff(
full_name VARCHAR(20),
birthday DATE,
phone VARCHAR(20),
mail VARCHAR(20),
id INT AUTO_INCREMENT NOT NULL,
date_started DATE,
salary INT,
PRIMARY KEY(id)
);

CREATE TABLE customer(
full_name VARCHAR(20),
birthday DATE,
phone VARCHAR(20),
mail VARCHAR(20),
id INT AUTO_INCREMENT NOT NULL,
expenditure INT,
ranking VARCHAR(10),
PRIMARY KEY(id)
);

CREATE TABLE product(
prod_code VARCHAR(20) NOT NULL,
prod_name VARCHAR(20),
category VARCHAR(20),
quantity INT,
price INT,
brand VARCHAR(20),
PRIMARY KEY(prod_code)
);

CREATE TABLE orders(
id INT AUTO_INCREMENT NOT NULL,
staff_id INT,
customer_id INT,
date_created DATE,
total_money INT,
PRIMARY KEY(id),
FOREIGN KEY (staff_id) REFERENCES staff(id),
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE order_line(
id INT AUTO_INCREMENT NOT NULL,
order_id INT,
product_code VARCHAR(20),
quantity int,
price int,
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (product_code) REFERENCES product(prod_code)
);

CREATE TABLE import_line(
id INT AUTO_INCREMENT NOT NULL,
staff_id INT,
product_code VARCHAR(20),
quantity int,
date_created DATE,
PRIMARY KEY(id),
FOREIGN KEY (product_code) REFERENCES product(prod_code),
FOREIGN KEY (staff_id) REFERENCES staff(id)
);