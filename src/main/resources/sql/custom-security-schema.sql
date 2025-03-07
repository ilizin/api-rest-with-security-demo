
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS members;

--
-- Table structure for table members
--

CREATE TABLE members (
  user_id varchar(50) NOT NULL,
  pw varchar(68) NOT NULL,
  active tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
);

--
-- Table structure for table authorities
--

CREATE TABLE roles (
  user_id varchar(50) NOT NULL,
  role varchar(50) NOT NULL,
  CONSTRAINT authorities_idx_1 UNIQUE (user_id, role),
  CONSTRAINT authorities5_ibfk_1 FOREIGN KEY (user_id) REFERENCES members (user_id)
);

