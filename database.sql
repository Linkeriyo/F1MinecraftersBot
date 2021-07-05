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

CREATE TABLE circuit(
    pk INTEGER PRIMARY KEY,
    circuitname TEXT NOT NULL,
    circuitcountry TEXT NOT NULL,
    infourl TEXT NOT NULL
);

CREATE TABLE hotlap(
    pk INTEGER PRIMARY KEY,
    lapcircuit INTEGER NOT NULL,
    laptime REAL NOT NULL,
    lapdate TEXT NOT NULL,
    FOREIGN KEY (lapcircuit) REFERENCES circuit(pk)
);