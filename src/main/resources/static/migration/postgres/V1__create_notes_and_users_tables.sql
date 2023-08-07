CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  login VARCHAR(50),
  password VARCHAR(100),
  role VARCHAR(255),
  CONSTRAINT UK_login_unique UNIQUE (login)
);
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE notes (
  id UUID DEFAULT uuid_generate_v4() NOT NULL,
  content VARCHAR(10000) DEFAULT NULL,
  privacy VARCHAR(255) DEFAULT NULL,
  title VARCHAR(100) DEFAULT NULL,
  user_id BIGINT DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FKechaouoa6kus6k1dpix1u91c FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX idx_user_id ON notes (user_id);