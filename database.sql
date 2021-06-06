CREATE TABLE user_profile(
    pk INTEGER PRIMARY KEY,
    discorduid TEXT NOT NULL
);

CREATE TABLE penalty(
    pk INTEGER PRIMARY KEY,
    penaltyuser INTEGER NOT NULL,
    penaltytime INTEGER NOT NULL,
    penaltydate TEXT NOT NULL,
    FOREIGN KEY(penaltyuser) REFERENCES user_profile(pk)
);

