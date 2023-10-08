insert into app_user values (0, 'admin', 'admin', 'admin', 'admin@admin', 'kakao', 'admin', 'Y', now(), now());



insert into apptech_app values (1, 0, '신한 쏠(SOL)', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (2, 0, '캐시워크', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (3, 0, '토스', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (4, 0, '허니스크린', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (5, 0, '해피머니', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (6, 0, '뱅크샐러드', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (7, 0, 'H.Point', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (8, 0, '카카오페이', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (9, 0, '더폴', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (10, 0, '하나머니', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (11, 0, '네이버페이', null, null, null, 'APPROVAL', now(), now());
insert into apptech_app values (12, 0, '터칭', null, null, null, 'APPROVAL', now(), now());

insert into apptech_review  values(1, 1, 0, 5, 'Good!', true, now(), now());
insert into apptech_review  values(2, 1, 0, 4, '포인트 잘 쌓여!',true,  now(), now());
insert into apptech_review  values(3, 1, 0, 5, 'Good!',true,  now(), now());


INSERT INTO public.apptech_profit
(app_id, user_id, profit_name, profit_desc, order_no, quiz_yn, start_date, end_date, admin_status, created_at, updated_at)
VALUES(1, 0, '쏠퀴즈', '퀴즈맞추거', 1,true, now(), now(), 'APPROVAL', now(), now());

INSERT INTO public.apptech_profit
(app_id, user_id, profit_name, profit_desc, order_no, quiz_yn, start_date, end_date, admin_status, created_at, updated_at)
VALUES(1, 0, '금모으기', '매일 출석하면 금도끼 ', 2,true, now(), now(), 'APPROVAL', now(), now());