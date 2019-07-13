CREATE TABLE "user"
(
    id         BIGSERIAL        NOT NULL CONSTRAINT user_pkey PRIMARY KEY,
    active     BOOLEAN          NOT NULL,
    email      VARCHAR(255)     NOT NULL CONSTRAINT users_email_unique UNIQUE,
    first_name VARCHAR(60)      NOT NULL,
    last_name  VARCHAR(60)      NOT NULL,
    password   VARCHAR(255)     NOT NULL
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255)
);

