-- schema.sql
-- Copia de esquema para inicializar la base de datos al arrancar (dev/testing)

CREATE TABLE IF NOT EXISTS cuenta
(
    id             bigint auto_increment
        primary key,
    deshabilitada  bit         null,
    fecha_registro datetime(6) null,
    fondos         double      null,
    plan           tinyint     null,
    check (`plan` between 0 and 1)
);

CREATE TABLE IF NOT EXISTS cuenta_usuarios
(
    cuenta_id  bigint not null,
    usuario_id bigint null,
    constraint FKn2m3nqhyu579qm9vjvrr3utxo
        foreign key (cuenta_id) references cuenta (id)
);

CREATE TABLE IF NOT EXISTS usuario
(
    id          bigint auto_increment
        primary key,
    apellido    varchar(255) not null,
    email       varchar(255) not null,
    latitud     double       null,
    longitud    double       null,
    nombre      varchar(255) not null,
    nro_celular bigint       not null,
    rol         tinyint      null,
    password    varchar(255) not null,
    user_name   varchar(255) not null,
    check (`rol` between 0 and 2)
);

CREATE TABLE IF NOT EXISTS usuario_cuenta
(
    usuario_id bigint not null,
    cuenta_id  bigint not null,
    constraint FK786863q20ubnssqge1849mlfo
        foreign key (cuenta_id) references cuenta (id),
    constraint FK88rjmvfp5kfhm083e3bafjdnw
        foreign key (usuario_id) references usuario (id)
);

CREATE TABLE IF NOT EXISTS usuario_cuentas
(
    usuario_id bigint not null,
    cuenta_id  bigint null,
    constraint FKki2157umespv7frivl0gjmb7l
        foreign key (usuario_id) references usuario (id)
);

CREATE TABLE IF NOT EXISTS usuario_monopatines
(
    usuario_id   bigint not null,
    monopatin_id bigint null,
    constraint FKds32qwkkxye3p6t00pvng10sv
        foreign key (usuario_id) references usuario (id)
);
