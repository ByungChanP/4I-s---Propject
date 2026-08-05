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
VALUES (1, 1, '랭크', '', '자유 랭크 5인큐 구해요 골드 이상', '모든 포지션 가능. 마이크 필수.', current_timestamp, '5',0),
       (1, 2, '일반', '', '일겜 듀오 하실분', '어느 포지션이든 상관 없습니다. 즐겜하실분', current_timestamp, '2',1),
       (1, 3, '일반', '', '칼바람 5인큐 아무나', '어떤 분이든 OK 마이크 가능하신 분 환영', current_timestamp, '5',1),
       (2, 4, '사냥', '카오스 던전 같이 도실 분', '혼자 돌기 심심해서 대화하면서 같이 하실 분 찾아요.', '내용2-1', current_timestamp, '3',0),
       (2, 3, '사냥', '', '필드 사냥 같이 하실 분', '즐겁게 대화하면서 사냥하실 분', current_timestamp, '3',1),
       (2, 2, '레이드', '', '카멘 노말 파티원 모집', '카멘 노말 도전하실 분 모집합니다', current_timestamp, '6',0);