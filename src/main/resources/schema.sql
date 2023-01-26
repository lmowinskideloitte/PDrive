-----------------------------------
--         PDRIVE SCHEMA         --
--      INF143871 INF148199      --
--       INFORMATYKA  SEM5       --
-----------------------------------

CREATE TABLE user_types
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY,
    role        TEXT NOT NULL,
    description TEXT,

    CONSTRAINT pk__user_types PRIMARY KEY (id)
);

CREATE TABLE users
(
    id        INTEGER GENERATED ALWAYS AS IDENTITY,
    username  TEXT    NOT NULL,
    password  TEXT    NOT NULL,
    user_type INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT pk__users PRIMARY KEY (id),
    CONSTRAINT fk__users__user_types FOREIGN KEY (user_type) REFERENCES user_types (id),
    CONSTRAINT uq__users__username UNIQUE (username)
);


CREATE TABLE cards
(
    id              INTEGER GENERATED ALWAYS AS IDENTITY,
    balance         INTEGER NOT NULL,
    user_id         INTEGER NOT NULL,
    -- expiration date auto extends 1 month when user tops up the card
    expiration_date DATE DEFAULT (CURRENT_DATE + interval '1 month'),

    CONSTRAINT pk__cards PRIMARY KEY (id),
    CONSTRAINT fk__cards__users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE payments
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY,
    cost INTEGER NOT NULL,

    CONSTRAINT pk__payments PRIMARY KEY (id)
);

CREATE TABLE vehicle_types
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY,
    name        TEXT NOT NULL,
    description TEXT,

    CONSTRAINT pk__vehicle_types PRIMARY KEY (id)
);

CREATE TABLE vehicles
(
    id             INTEGER GENERATED ALWAYS AS IDENTITY,
    vehicle_type   INTEGER NOT NULL DEFAULT 0,
    trip_count     INTEGER          DEFAULT 0,
    battery_charge INTEGER          DEFAULT 100,

    CONSTRAINT pk__vehicles PRIMARY KEY (id),
    CONSTRAINT fk__vehicles__vehicle_type FOREIGN KEY (vehicle_type) REFERENCES vehicle_types (id),
    CONSTRAINT ch__vehicles__battery_charge_range CHECK ( battery_charge >= 0 AND battery_charge <= 100)
);

CREATE TABLE locations
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY,
    city     TEXT NOT NULL,
    district TEXT,

    CONSTRAINT pk__locations PRIMARY KEY (id)
);

CREATE TABLE stations
(
    id             INTEGER GENERATED ALWAYS AS IDENTITY,
    address        TEXT NOT NULL,
    vehicles_count INTEGER DEFAULT 0,

    CONSTRAINT pk__stations PRIMARY KEY (id)
);

CREATE TABLE trips
(
    id                     INTEGER GENERATED ALWAYS AS IDENTITY,
    card_id                INTEGER NOT NULL,
    payment_id             INTEGER NOT NULL,
    origin_station_id      INTEGER NOT NULL,
    destination_station_id INTEGER NOT NULL,
    distance               INTEGER NOT NULL,
    vehicle_id             INTEGER NOT NULL,

    CONSTRAINT pk__trips PRIMARY KEY (id),
    CONSTRAINT fk__trips__cards FOREIGN KEY (card_id) REFERENCES cards (id),
    CONSTRAINT fk__trips__payments FOREIGN KEY (payment_id) REFERENCES payments (id),
    CONSTRAINT fk__trips_origin__stations FOREIGN KEY (origin_station_id) REFERENCES stations (id),
    CONSTRAINT fk__trips_destination__stations FOREIGN KEY (destination_station_id) REFERENCES stations (id),
    CONSTRAINT fk__trips__vehicles FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);

CREATE TABLE event_types
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY,
    name        TEXT NOT NULL,
    description TEXT,

    CONSTRAINT pk__event_types PRIMARY KEY (id)
);

CREATE TABLE events
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY,
    event_type  INTEGER                  DEFAULT 0,
    trip_id     INTEGER,
    vehicle_id  INTEGER NOT NULL,
    report_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_DATE,
    description TEXT,

    CONSTRAINT pk__events PRIMARY KEY (id),
    CONSTRAINT fk__events__event_types FOREIGN KEY (event_type) REFERENCES event_types (id),
    CONSTRAINT fk__events__trips FOREIGN KEY (trip_id) REFERENCES trips (id),
    CONSTRAINT fk__events__vehicles FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);

