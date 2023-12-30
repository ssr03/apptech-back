-- DROP TABLE app_user;
CREATE TABLE "app_user" (
   id bigserial NOT NULL,
   username varchar(50) NULL DEFAULT NULL,
   "password" varchar(100) NULL DEFAULT NULL,
   nickname varchar(50) NULL DEFAULT NULL,
   email varchar(100) NULL DEFAULT NULL,
   sns_provider varchar(255) NOT NULL,
   sns_id varchar(100) NOT NULL DEFAULT '',
   admin_yn bool NOT NULL DEFAULT false,
   created_at timestamp NOT NULL,
   updated_at timestamp NULL,
   CONSTRAINT user_pkey PRIMARY KEY (id)
);

-- DROP TABLE apptech_user_profit;
CREATE TABLE "apptech_user_profit" (
   id bigserial NOT NULL,
   user_id int8 NOT NULL,
   app_id int8 NOT NULL,
   profit_date date NULL,
   profit int8 DEFAULT 0,
   profit_image_file varchar(255),
   admin_status char(2) DEFAULT 'Y',
   created_at timestamp NOT NULL,
   updated_at timestamp NULL,
   PRIMARY KEY (id)
);

-- DROP TABLE apptech_app;
CREATE TABLE apptech_app (
    id int8 NOT NULL,
    user_id int8 NOT NULL,
    app_name varchar(100) NULL DEFAULT NULL::character varying,
    app_logo_file varchar(255) NULL DEFAULT NULL::character varying,
    app_ios_link varchar(8000) NULL DEFAULT NULL::character varying,
    app_android_link varchar(8000) NULL DEFAULT NULL::character varying,
    admin_status varchar(20) NULL DEFAULT NULL::character varying,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    CONSTRAINT apptech_app_pk PRIMARY KEY (id),
    CONSTRAINT app_user_fk FOREIGN KEY (user_id) REFERENCES app_user(id)
);

create sequence apptech_app_id_seq start with 100;
alter table apptech_app  alter column id set default nextVal('apptech_app_id_seq');


-- DROP TABLE apptech_profit;
CREATE TABLE apptech_profit (
   id int8 NOT NULL,
   app_id int8 NOT NULL,
   user_id int8 NOT NULL,
   profit_name varchar(255) NULL DEFAULT NULL::character varying,
   profit_desc varchar(8000) NULL DEFAULT NULL::character varying,
   order_no int2 NOT NULL,
   quiz_yn bool NULL,
   start_date timestamp NULL,
   end_date timestamp NULL,
   admin_status varchar(20) NULL DEFAULT ''::character varying,
   created_at timestamp NOT NULL,
   updated_at timestamp NULL,
   CONSTRAINT apptech_profit_pk PRIMARY KEY (id),
   CONSTRAINT app_user_fk FOREIGN KEY (user_id) REFERENCES app_user(id),
   CONSTRAINT apptech_app_fk FOREIGN KEY (app_id) REFERENCES apptech_app(id)
);

create sequence apptech_profit_id_seq start with 1;
alter table apptech_profit  alter column id set default nextVal('apptech_profit_id_seq');

-- DROP TABLE apptech_profit_quiz;
CREATE TABLE apptech_profit_quiz (
    id int8 NOT NULL,
    app_profit_id int8 NOT NULL,
    user_id int8 NOT NULL,
    quiz_date timestamp NULL,
    quiz varchar(8000) NULL DEFAULT NULL::character varying,
    answer varchar(8000) NULL DEFAULT NULL::character varying,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    CONSTRAINT apptech_profit_quiz_pk PRIMARY KEY (id),
    CONSTRAINT app_user_fk FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT apptech_profit_fk FOREIGN KEY (app_profit_id) REFERENCES apptech_profit(id)
);

create sequence apptech_profit_quiz_id_seq start with 100;
alter table apptech_profit_quiz alter column id set default nextVal('apptech_profit_quiz_id_seq');

-- DROP TABLE apptech_profit_quiz_favorite;
CREATE TABLE public.apptech_profit_favorite (
        id int8 NOT NULL,
        app_profit_id int8 NOT NULL,
        user_id int8 NOT NULL,
        created_at timestamp NOT NULL,
        CONSTRAINT apptech_profit_favorite_pk PRIMARY KEY (id)
);

create sequence apptech_profit_quiz_favorite_id_seq start with 1;
alter table apptech_profit_quiz_favorite  alter column id set default nextVal('apptech_profit_quiz_favorite_id_seq');


-- DROP TABLE apptech_profit_quiz_correct;
CREATE TABLE apptech_profit_quiz_correct (
    id bigserial NOT NULL,
    app_profit_quiz_id int8 NOT NULL,
    user_id int8 NOT NULL,
    correct_status varchar(10) NULL DEFAULT ''::character varying,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    CONSTRAINT apptech_profit_quiz_correct_pk PRIMARY KEY (id),
    CONSTRAINT app_user_fk FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT apptech_profit_quiz_fk FOREIGN KEY (app_profit_quiz_id) REFERENCES apptech_profit_quiz(id)
);

create sequence apptech_profit_quiz_correct_id_seq start with 100;
alter table apptech_profit_quiz_correct alter column id set default nextVal('apptech_profit_quiz_correct_id_seq');





-- DROP TABLE apptech_review;
CREATE TABLE public.apptech_review (
                                       id int8 NOT NULL,
                                       app_id int8 NOT NULL,
                                       user_id int8 NOT NULL,
                                       rate int2 NULL,
                                       review varchar(255) NULL DEFAULT NULL::character varying,
                                       use_yn bool NULL,
                                       created_at timestamp NOT NULL,
                                       updated_at timestamp NULL,
                                       CONSTRAINT apptech_review_pk PRIMARY KEY (id)
);

create sequence apptech_review_seq start with 1;
alter table apptech_review  alter column id set default nextVal('apptech_review_seq');




-- DROP TABLE apptech_rank;
CREATE TABLE "apptech_rank" (
    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    app_id int8 NOT NULL,
    type char(10) NOT NULL DEFAULT 'DAY',
    ranking_no int8 NOT NULL DEFAULT 0,
    profit_date date NULL,
    total_profit int8 DEFAULT 0,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    PRIMARY KEY (id)
);