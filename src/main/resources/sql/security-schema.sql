DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

-- You have to use the exact same table names and the exact same column names in order to spring security
-- authentication and authorization work

--
-- Table structure for table users
--

CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(68) NOT NULL, -- {bcrypt} = 8 characters + encoded password = 60 characters
  enabled tinyint NOT NULL,
  PRIMARY KEY (username)
);

--
-- Table structure for table `authorities`
--

CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  CONSTRAINT authorities_idx_1 UNIQUE (username, authority),
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
);