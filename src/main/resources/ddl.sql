CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE "user" (
    id INTEGER PRIMARY KEY DEFAULT nextval('user_id_seq'),
    name VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL DEFAULT NOW()
);

ALTER SEQUENCE user_id_seq OWNED BY "user".id;

CREATE SEQUENCE ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE Ticket (
    id INTEGER PRIMARY KEY DEFAULT nextval('ticket_id_seq'),
    user_id INTEGER NOT NULL,
    ticket_type ticket_type NOT NULL,
    creation_date DATE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

ALTER SEQUENCE ticket_id_seq OWNED BY Ticket.id;