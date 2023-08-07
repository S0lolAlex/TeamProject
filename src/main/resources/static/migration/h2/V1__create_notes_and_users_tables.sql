CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  login varchar(50) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  role varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE (login)
);

CREATE TABLE notes (
  id UUID NOT NULL,
  content varchar(10000) DEFAULT NULL,
  privacy varchar(255) DEFAULT NULL,
  title varchar(100) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FKechaouoa6kus6k1dpix1u91c FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX FKechaouoa6kus6k1dpix1u91c ON notes (user_id);