# DROP TABLE IF EXISTS member;
CREATE TABLE IF NOT EXISTS member
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(50) NOT NULL,
    pw         VARCHAR(20) NOT NULL,
    name       VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

# DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(20) REFERENCES member (id) ON DELETE RESTRICT,
    game_id     int REFERENCES game (id) ON DELETE CASCADE NOT NULL,
    title       VARCHAR(30)                                NOT NULL,
    content     TEXT                                       NOT NULL,
    max_count   INT      DEFAULT 0,
    reply_count INT      DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

# DROP TABLE IF EXISTS reply;
CREATE TABLE IF NOT EXISTS reply
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    member_id  VARCHAR(20) REFERENCES member (id) ON DELETE RESTRICT,
    post_id    INT REFERENCES post (id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

# DROP TABLE IF EXISTS game
CREATE TABLE IF NOT EXISTS game
(
    id                 INT PRIMARY KEY,
    name               VARCHAR(50) UNIQUE NOT NULL,
    background_img_dir TEXT,
    game_logo_dir      TEXT,
    detail             TEXT
)