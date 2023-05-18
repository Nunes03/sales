create table cliente
(
    id   integer primary key auto_increment,
    nome varchar(100)
);

create table produto
(
    id             integer primary key auto_increment,
    descricao      varchar(100),
    preco_unitario numeric(20, 2)
);

create table pedido
(
    id         integer primary key auto_increment,
    data       timestamp      not null,
    total      numeric(20, 2) not null,
    cliente_id integer        not null references cliente (id)
);

create table itens_pedido
(
    id         integer primary key auto_increment,
    quantidade integer not null,
    pedido_id  integer not null references pedido (id),
    produto_id integer not null references produto (id)
);