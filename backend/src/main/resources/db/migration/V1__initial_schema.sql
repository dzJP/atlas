CREATE TABLE businesses
(
    id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    name VARCHAR(255) NOT NULL,
    industry VARCHAR(255) NOT NULL,
    country VARCHAR(255),
    timezone VARCHAR(255),
    active BOOLEAN NOT NULL,

    CONSTRAINT pk_businesses PRIMARY KEY (id)
);

CREATE TABLE users
(
    id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    business_id UUID NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    first_name VARCHAR(255),
    last_name VARCHAR(255),

    role VARCHAR(20) NOT NULL,

    enabled BOOLEAN NOT NULL,

    last_login TIMESTAMP WITH TIME ZONE,

    CONSTRAINT pk_users PRIMARY KEY (id),

    CONSTRAINT fk_user_business
        FOREIGN KEY (business_id)
            REFERENCES businesses(id)
);