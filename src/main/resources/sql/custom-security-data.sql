--
-- Inserting data for table members
--

INSERT INTO members
VALUES
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);

--
-- Inserting data for table roles
--

INSERT INTO roles
VALUES
('john','ROLE_USER'),
('mary','ROLE_USER'),
('mary','ROLE_MANAGER'),
('susan','ROLE_USER'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');