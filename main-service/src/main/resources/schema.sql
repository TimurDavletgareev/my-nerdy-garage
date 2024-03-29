-- drop table IF EXISTS works_parts, parts, works, categories, vehicles, users CASCADE;

CREATE TABLE IF NOT EXISTS users (

    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    birth_date      TIMESTAMP,
    reg_date        TIMESTAMP,
    password        VARCHAR(255) NOT NULL,

    CONSTRAINT      pk_user PRIMARY KEY (id),

    CONSTRAINT      UQ_USER_NAME UNIQUE (name),

    CONSTRAINT      UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS vehicles (

    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,

    owner_id        BIGINT NOT NULL, -- fk

    producer        VARCHAR(255) NOT NULL,
    model           VARCHAR(255) NOT NULL,
    name            VARCHAR(255),
    color           VARCHAR(255),
    reg_number      VARCHAR(255),
    release_date    TIMESTAMP,
    engine_volume   DEC,
    fuel_type       VARCHAR(255),
    power           INTEGER,
    description     VARCHAR(255),

    CONSTRAINT  pk_vehicle PRIMARY KEY (id),

    CONSTRAINT  fk_veh_owner_id
        FOREIGN KEY (owner_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT  UQ_VEH_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS categories (

    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,

    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(255),

    creator_id      BIGINT, -- fk

    CONSTRAINT  pk_category PRIMARY KEY (id),

    CONSTRAINT  fk_cat_creator_id
        FOREIGN KEY (creator_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS works (

    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,

    initiator_id    BIGINT NOT NULL, -- fk

    vehicle_id      BIGINT NOT NULL, -- fk

    category_id     BIGINT NOT NULL, -- fk

    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255),

    status          VARCHAR(255),
    start_date      TIMESTAMP,
    end_date        TIMESTAMP,

    CONSTRAINT  pk_work PRIMARY KEY (id),

    CONSTRAINT  fk_work_initiator_id
        FOREIGN KEY (initiator_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT  fk_work_vehicle_id
        FOREIGN KEY (vehicle_id)
            REFERENCES vehicles (id)
            ON DELETE CASCADE,

    CONSTRAINT  fk_work_category_id
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS parts (

    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,

    owner_id        BIGINT NOT NULL, -- fk

    vehicle_id      BIGINT, -- fk

    category_id     BIGINT NOT NULL, -- fk

    part_number     VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(255),
    is_reusable     Boolean NOT NULL,
    status          VARCHAR(255),
    order_date      TIMESTAMP,
    delivery_date   TIMESTAMP,

    CONSTRAINT  pk_part PRIMARY KEY (id),

    CONSTRAINT  fk_part_owner_id
        FOREIGN KEY (owner_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT  fk_part_vehicle_id
        FOREIGN KEY (vehicle_id)
            REFERENCES vehicles (id)
            ON DELETE CASCADE,

    CONSTRAINT  fk_part_category_id
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS works_parts (

    work_id             BIGINT NOT NULL, -- fk
    part_id             BIGINT NOT NULL, -- fk

	CONSTRAINT  pk_works_parts PRIMARY KEY (work_id, part_id), -- composite key

    CONSTRAINT  fk_work_id
        FOREIGN KEY (work_id)
            REFERENCES works (id)
            ON DELETE CASCADE,

    CONSTRAINT  fk_part_id
        FOREIGN KEY (part_id)
            REFERENCES parts (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (

    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,

    author_id               BIGINT NOT NULL,

    comment_object_class    VARCHAR(255) NOT NULL,

    comment_object_id       BIGINT NOT NULL,

    title                   VARCHAR(255),
    content                 VARCHAR(255) NOT NULL,
    date_time               TIMESTAMP NOT NULL
);
