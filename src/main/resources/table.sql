# DROP TABLE IF EXISTS member;
CREATE TABLE IF NOT EXISTS member
(
    id         VARCHAR(20) PRIMARY KEY,
    pw         VARCHAR(20) NOT NULL,
    name       VARCHAR(20) NOT NULL,
    email      VARCHAR(50),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

# DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(20) REFERENCES member (id) ON DELETE RESTRICT,
    title       VARCHAR(30) NOT NULL,
    category    VARCHAR(20) NOT NULL,
    view_count  INT      DEFAULT 0,
    reply_count INT      DEFAULT 0,
    content     TEXT        NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

# DROP TABLE IF EXISTS reply;
CREATE TABLE IF NOT EXISTS reply
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    member_id  VARCHAR(20) REFERENCES member (id) ON DELETE RESTRICT,
    post_id    INT REFERENCES post (id) ON DELETE CASCADE,
    reply_id   INT REFERENCES reply (id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
)