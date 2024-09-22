INSERT INTO public."user"("name")
VALUES
	('bimo'),
	('fin'),
	('jake');

INSERT INTO public.ticket(user_id, ticket_type)
VALUES
    (1, 'DAY'),
    (1, 'WEEK'),
    (2, 'MONTH'),
    (3, 'YEAR');
    
--INSERT INTO public.ticket(user_id, ticket_type) VALUES(1, 'DECADE'),