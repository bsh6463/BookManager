--call next value for hibernate_sequence;
insert into user(id, name, email, created_at, updated_at)
values (1, 'hyun', 'hyun@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user(id, name, email, created_at, updated_at)
values (2, 'park', 'park@google.com', now(), now());

--call next value for hibernate_sequence;
insert into user(id, name, email, created_at, updated_at)
values (3, 'lee', 'lee@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user(id, name, email, created_at, updated_at)
values (4, 'kim', 'kim@google.com', now(), now());

--call next value for hibernate_sequence;
insert into user(id, name, email, created_at, updated_at)
values (5, 'hyun', 'hyun@google.com', now(), now());

insert into publisher(id, name) values (1, '탐사수');

insert into book(id, name, publisher_id, `deleted`, `status`) values (1, '탐사수수수수', 1, false, 100);

insert into book(id, name, publisher_id, `deleted`, `status`) values (2,'삼다수', 1, false, 200);

insert into book(id, name, publisher_id, `deleted`, `status`) values (3, '백산수', 1, true, 100);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (1, 'JPA', '오우야', 5.0, 1, 1);
insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (2, '백엔드', 'ㄷㄷㄷ', 1.0, 2, 2);

insert into comment(`id`, `comment`, `review_id`) values (1, '킹치만..', 1);
insert into comment(`id`, `comment`, `review_id`) values (2, '오히려좋아', 1);
insert into comment(`id`, `comment`, `review_id`) values (3, '오히려안좋아', 2);
