CREATE TABLE IF NOT EXISTS accounts (
    account_id UUID PRIMARY KEY,
    client_id UUID,
    balance DECIMAL(19,4)
);