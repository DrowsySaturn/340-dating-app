/*
 * The purpose of this sql file is to create necessary tables in the database.
 */

CREATE TABLE IF NOT EXISTS IntroVideos (
    IntroVideo_ID BIGINT AUTO_INCREMENT,
    PRIMARY KEY (IntroVideo_ID)
);

CREATE TABLE IF NOT EXISTS Profiles (
    Profile_ID BIGINT AUTO_INCREMENT,
    Profile_Age INT,
    Profile_Name VARCHAR(128),
    Profile_Message VARCHAR(512),
    IntroVideo_ID BIGINT,
    PRIMARY KEY (Profile_ID),
    FOREIGN KEY (IntroVideo_ID) REFERENCES IntroVideos(IntroVideo_ID)
)
