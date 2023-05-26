CREATE TABLE IF NOT EXISTS report (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identity VARCHAR(36) NOT NULL,
    about VARCHAR NOT NULL,
    date DATE NOT NULL,
    state VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    description TEXT NOT NULL
);