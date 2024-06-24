CREATE DATABASE apidomain;

CREATE TYPE category AS ENUM ('PORNOGRAPHY', 'GAMBLING', 'VIOLENCE', 'DRUGS', 'SOCIAL_MEDIA', 'MALWARE_AND_PHISHING');

CREATE TABLE IF NOT EXISTS domain (
    id        BIGSERIAL   NOT NULL,
    domain    TEXT        NOT NULL UNIQUE,
    category  category    NOT NULL,
    blocked   BOOLEAN     NOT NULL,

    CONSTRAINT pk_domain_id PRIMARY KEY (id)
);

INSERT INTO domain (domain, category, blocked) VALUES ('sex.badinternetdomain.com',         'PORNOGRAPHY',          TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('porn.badinternetdomain.com',        'PORNOGRAPHY',          FALSE);
INSERT INTO domain (domain, category, blocked) VALUES ('gambling.badinternetdomain.com',    'GAMBLING',             TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('casino.badinternetdomain.com',      'GAMBLING',             FALSE);
INSERT INTO domain (domain, category, blocked) VALUES ('violence.badinternetdomain.com',    'VIOLENCE',             TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('damage.badinternetdomain.com',      'VIOLENCE',             FALSE);
INSERT INTO domain (domain, category, blocked) VALUES ('drugs.badinternetdomain.com',       'DRUGS',                TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('narcotic.badinternetdomain.com',    'DRUGS',                FALSE);
INSERT INTO domain (domain, category, blocked) VALUES ('communities.badinternetdomain.com', 'SOCIAL_MEDIA',         TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('social.badinternetdomain.com',      'SOCIAL_MEDIA',         FALSE);
INSERT INTO domain (domain, category, blocked) VALUES ('phishing.badinternetdomain.com',    'MALWARE_AND_PHISHING', TRUE);
INSERT INTO domain (domain, category, blocked) VALUES ('malware.badinternetdomain.com',     'MALWARE_AND_PHISHING', FALSE);

CREATE TABLE IF NOT EXISTS statistics (
    id        BIGSERIAL   NOT NULL,
    count_of_blocked_malware_and_fishing    bigint NOT NULL,
    count_of_blocked_not_malware_and_fishing    bigint NOT NULL,

    CONSTRAINT pk_statistics_id PRIMARY KEY (id)
);

INSERT INTO statistics(id, count_of_blocked_malware_and_fishing, count_of_blocked_not_malware_and_fishing)
  VALUES (1, 0, 0);