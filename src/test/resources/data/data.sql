
create table pessoa (
    id int auto_increment( 10, 1 ),
    nome varchar( 200 ),
    data_nascimento timestamp,
    primary key( id )
);

create table endereco (
    id int auto_increment( 10, 1 ),
    logradouro varchar( 200 ),
    cep varchar( 100 ),
    numero varchar( 100 ),
    cidade varchar( 200 ),
    estado varchar( 200 ),
    principal boolean,
    pessoa_id int,
    primary key( id ),
    foreign key( pessoa_id ) references pessoa( id )
);

insert into pessoa ( id, nome, data_nascimento ) values ( 1, 'abc', current_timestamp );
insert into pessoa ( id, nome, data_nascimento ) values ( 2, 'bca', current_timestamp );
insert into pessoa ( id, nome, data_nascimento ) values ( 3, 'ddd', current_timestamp );
insert into pessoa ( id, nome, data_nascimento ) values ( 4, 'ggg', current_timestamp );

insert into endereco ( id, cidade, estado, principal, pessoa_id ) values ( 1, 'A', 'A', true, 1 );
insert into endereco ( id, cidade, estado, principal, pessoa_id ) values ( 2, 'B', 'B', true, 2 );
insert into endereco ( id, cidade, estado, principal, pessoa_id ) values ( 3, 'C', 'C', true, 3 );
insert into endereco ( id, cidade, estado, principal, pessoa_id ) values ( 4, 'D', 'D', true, 4 );