CREATE TABLE loan_applications
(
    id BIGSERIAL PRIMARY KEY,

    loan_amount NUMERIC(15, 2) NOT NULL,

    term_months INTEGER NOT NULL,

    monthly_income NUMERIC(15, 2) NOT NULL,

    current_credit_load NUMERIC(15, 2) NOT NULL,

    credit_rating INTEGER NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);