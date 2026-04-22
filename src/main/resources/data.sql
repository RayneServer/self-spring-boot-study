insert into article(title, content)
values
    ('아오쿠모 린', '사랑해'),
    ('쿠모린', '귀여워'),
    ('뇨봇', '뇨'),
    ('린의 인생 치킨은?', '댓글 고'),
    ('린의 인생 야채는?', '댓글 고고'),
    ('린의 취미는?', '댓글 고고고');

insert into comment(article_id, nickname, body)
values
    (4, '유니', '부르다끄'),
    (4, '후야', '굽네 바사삭'),
    (4, '히나', '뿌링클'),
    (5, '유니', '습박 야채를 왜 먹여'),
    (5, '리제', '토마토'),
    (5, '타비', '양배추'),
    (6, '유니', '이리'),
    (6, '마시로', '이리하기'),
    (6, '타비', '이리 납치');

insert into member (email, password)
values ('kumorin', '0000'),
       ('Aoaoao', '1234'),
       ('Aokumo Rin', '5678');

insert into coffee(name, price)
values ('아메리카노', 4500),
       ('카페라떼', 5000),
       ('카페모카', 5500);