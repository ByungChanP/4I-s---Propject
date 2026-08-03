 DROP TABLE IF EXISTS user_info;
 DROP TABLE IF EXISTS post;
 DROP TABLE IF EXISTS ingame_info;
 DROP TABLE IF EXISTS game;
 DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS game
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(50) UNIQUE NOT NULL,
    background_img_dir TEXT,
    game_logo_dir      TEXT,
    detail             TEXT
);

CREATE TABLE IF NOT EXISTS member
(
    id              int AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(50) NOT NULL,
    pw              VARCHAR(20) NOT NULL,
    name            VARCHAR(20) NOT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    profile_img_dir TEXT
);

CREATE TABLE IF NOT EXISTS post
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    game_id           INT REFERENCES game (id) ON DELETE CASCADE,
    member_id         INT REFERENCES member (id) ON DELETE RESTRICT,
    tag               VARCHAR(10) NOT NULL,
    min_rank          VARCHAR(15) NOT NULL,
    max_rank          VARCHAR(15) NOT NULL,
    restrictions      TEXT,
    title             VARCHAR(30) NOT NULL,
    content           TEXT        NOT NULL,
    max_count         INT      DEFAULT 0,
    participant_count INT      DEFAULT 1,
    deadline          DATETIME    NOT NULL,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS user_info
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    member_id  INT REFERENCES member (id) ON DELETE RESTRICT,
    post_id    INT REFERENCES post (id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ingame_info
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    game_id     INT REFERENCES game (id) ON DELETE CASCADE,
    member_id   INT REFERENCES member (id) ON DELETE CASCADE,
    ingame_info TEXT
);
