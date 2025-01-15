-- !important do not change the version file once it is created
-- !important name : V1 __ use 2 * __
-- ! if you want to alter a table, please create a new file.sql where you alter it
-- ! try to be consistent with the version number :) V uppercase , number

-- Create table for users
create TABLE Users(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    score BIGINT NOT NULL
)

-- Create table for tokens
CREATE TABLE Token (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       token_type VARCHAR(255) NOT NULL,
                       revoked BOOLEAN NOT NULL,
                       expired BOOLEAN NOT NULL,
                       user_id BIGINT NOT NULL REFERENCES Users(id) ON DELETE CASCADE
);

-- Create table for forms
CREATE TABLE Forms (
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      score BIGINT NOT NULL,
                      anwsers VARCHAR(1000) NOT NULL,
                      user_id BIGINT NOT NULL REFERENCES Users(id) ON DELETE CASCADE
);