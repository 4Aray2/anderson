ALTER TABLE "user" RENAME TO users;

ALTER SEQUENCE user_id_seq RENAME TO users_id_seq;

ALTER SEQUENCE users_id_seq OWNED BY users.id;

ALTER TABLE Ticket
    DROP CONSTRAINT Ticket_user_id_fkey,
    ADD CONSTRAINT Ticket_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);

ALTER INDEX user_pkey RENAME TO users_pkey;
