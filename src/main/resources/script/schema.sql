CREATE TABLE "app_user" (
   id int8 NOT NULL,
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