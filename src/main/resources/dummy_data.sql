INSERT INTO game(game_title, detail, background_img_dir, game_logo_dir)
VALUES ('League Of Legend', '자세한 설명은 생략한다', '/images/lol/lol-banner.png', '/images/lol/lol_logo.png'),
       ('Lost Ark', '자세한 설명은 생략한다', '/images/lostark/lostark-banner.png', '/images/lostark/lostark-logo.png');

INSERT INTO member(email, pw, nickname)
VALUES ('user1@example.com', '1111', 'user1'),
       ('user2@example.com', '1111', 'user2'),
       ('user3@example.com', '1111', 'user3'),
       ('user4@example.com', '1111', 'user4');

INSERT INTO post(game_id, member_id, tag, min_rank, max_rank, restrictions, title, content, deadline, max_count)
VALUES (1, 1, '일반', '', '', '', '게시글1-1', '내용1-1', current_timestamp, '5'),
       (1, 2, '일반', '', '', '', '게시글1-2', '내용1-2', current_timestamp, '2'),
       (1, 3, '일반', '', '', '', '게시글1-3', '내용1-3', current_timestamp, '5'),
       (2, 4, '사냥', '', '', '', '게시글2-1', '내용2-1', current_timestamp, '3'),
       (2, 3, '사냥', '', '', '', '게시글2-2', '내용2-2', current_timestamp, '3'),
       (2, 2, '레이드', '', '', '', '게시글2-3', '내용2-3', current_timestamp, '6');