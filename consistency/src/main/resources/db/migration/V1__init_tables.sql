create table coupon(
    id bigint not null auto_increment,
    coupon_name varchar(255) not null,
    total bigint not null,
    created_at  timestamp(6) not null,
    modified_at timestamp(6) not null,
    primary key (`id`)
);

create table user_coupon(
    id bigint not null auto_increment,
    coupon_id bigint not null,
    user_id varchar(255) not null,
    created_at  timestamp(6) not null,
    modified_at timestamp(6) not null,
    primary key (`id`)
);


