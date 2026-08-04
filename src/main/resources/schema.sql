-- 1. 외래키 제약 조건 일시 해제
SET FOREIGN_KEY_CHECKS = 0;

-- 2. 테이블 삭제 (순서에 상관없이 에러 없이 삭제됨)
DROP TABLE IF EXISTS participant_info;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS ingame_info;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS member;

-- 3. 외래키 제약 조건 재활성화 (필수)
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS game
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    game_title         VARCHAR(50) UNIQUE NOT NULL,
    background_img_dir TEXT,
    game_logo_dir      TEXT,
    detail             TEXT
);

CREATE TABLE IF NOT EXISTS member
(
    id              int AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(50) UNIQUE NOT NULL,
    pw              VARCHAR(20)        NOT NULL,
    nickname        VARCHAR(20)        NOT NULL,
    created_at      DATETIME     DEFAULT CURRENT_TIMESTAMP,
    profile_img_dir VARCHAR(255) DEFAULT '/images/icon/person-circle.svg'
);

CREATE TABLE IF NOT EXISTS post
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    game_id           INT REFERENCES game (id) ON DELETE CASCADE,
    member_id         INT REFERENCES member (id) ON DELETE RESTRICT,
    tag               VARCHAR(10) NOT NULL,
    restrictions      TEXT,
    title             VARCHAR(30) NOT NULL,
    content           TEXT        NOT NULL,
    max_count         INT      DEFAULT 1,
    participant_count INT      DEFAULT 1,
    deadline          DATETIME    NOT NULL,
    is_closed         BOOLEAN  DEFAULT FALSE,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS participant_info
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
