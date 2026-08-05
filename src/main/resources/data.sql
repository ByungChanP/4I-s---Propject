INSERT INTO game(game_title, detail, background_img_dir, game_logo_dir)
VALUES ('League Of Legend', '치열한 전략과 팀워크의 전장! 함께 승리를 만들어갈 소환사를 만나보세요.', '/images/lol/lol-banner.png', '/images/lol/lol_logo.png'),
       ('Lost Ark', '광활한 아크라시아에서 레이드와 모험을 함께할 동료를 찾아보세요.', '/images/lostark/lostark-banner.png', '/images/lostark/lostark-logo.png'),
       ('OverWatch', '영웅들의 개성 넘치는 전투, 최고의 팀플레이를 완성해 보세요.', '/images/overwatch/overwatch-banner.jpg', '/images/overwatch/overwatch-logo.webp'),
       ('Valorant', '에임과 전략이 승부를 가르는 전술 FPS, 최고의 팀을 만들어 보세요.', '/images/valorant/valorant-banner.webp', '/images/valorant/valorant-logo.webp'),
       ('PUBG', '완벽한 브리핑과 팀플레이, 함께할 스쿼드를 지금 만나보세요.', '/images/battleground/battleground-banner.png', '/images/battleground/battleground-logo.png'),
       ('Maple Story', '추억은 그대로, 재미는 두 배! 메이플 친구들과 함께 성장해 보세요.', '/images/maplestory/maple-banner.webp', '/images/maplestory/maple-logo.png');

INSERT INTO member(email, pw, nickname)
VALUES ('user1@example.com', '1111', 'user1'),
       ('user2@example.com', '1111', 'user2'),
       ('user3@example.com', '1111', 'user3'),
       ('user4@example.com', '1111', 'user4');

INSERT INTO post(game_id, member_id, tag, restrictions, title, content, deadline, max_count,is_closed)
VALUES (1, 1, '랭크', '', '게시글1-1', '내용1-1', current_timestamp, '5',0),
       (1, 2, '일반', '', '게시글1-2', '내용1-2', current_timestamp, '2',1),
       (1, 3, '일반', '', '게시글1-3', '내용1-3', current_timestamp, '5',1),
       (2, 4, '사냥', '', '게시글2-1', '내용2-1', current_timestamp, '3',0),
       (2, 3, '사냥', '', '게시글2-2', '내용2-2', current_timestamp, '3',1),
       (2, 2, '레이드', '', '게시글2-3', '내용2-3', current_timestamp, '6',0);