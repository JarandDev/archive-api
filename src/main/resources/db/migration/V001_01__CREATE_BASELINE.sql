CREATE TABLE archive
(
    id UUID PRIMARY KEY
);

CREATE TABLE bread_bags_item
(
    id                    INT PRIMARY KEY,
    date                  VARCHAR NOT NULL,
    shop                  VARCHAR,
    baker1                VARCHAR,
    established           VARCHAR,
    baker2                VARCHAR,
    place                 VARCHAR,
    series                VARCHAR,
    bread_name            VARCHAR,
    description           VARCHAR,
    composition           VARCHAR,
    health_claim          VARCHAR,
    name                  VARCHAR,
    support               VARCHAR,
    motive                VARCHAR,
    bag_color             VARCHAR,
    coarseness            VARCHAR,
    coarseness_percentage VARCHAR,
    fibre_percentage      VARCHAR,
    weight                VARCHAR,
    price                 VARCHAR,
    keyhole               VARCHAR,
    comment               VARCHAR,
    price_per_kg          VARCHAR,
    archive_id            UUID    NOT NULL REFERENCES archive (id)
);

CREATE TABLE image
(
    image_front        VARCHAR,
    image_back         VARCHAR,
    bread_bags_item_id INT NOT NULL REFERENCES bread_bags_item (id)
);
