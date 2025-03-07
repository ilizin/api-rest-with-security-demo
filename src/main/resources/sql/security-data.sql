--
-- Inserting data for table `users`
--

INSERT INTO users
VALUES
-- between '{}' the encoding algorithm id
-- The plain text password is fun123
('john','{bcrypt}$2a$12$6dIc5uvtDwSaUnQXbVcUoeCmzVVJGHedhTumn7wjjud/grM4EeKhW',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);

--
-- Inserting data for table authorities
--

INSERT INTO authorities
VALUES
('john','ROLE_USER'),
('mary','ROLE_USER'),
('mary','ROLE_MANAGER'),
('susan','ROLE_USER'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');