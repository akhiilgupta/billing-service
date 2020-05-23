CREATE OR REPLACE FUNCTION set_created_timestamp()
    RETURNS TRIGGER AS
$$
DECLARE
    currentTime TIMESTAMP WITHOUT time zone := now();
BEGIN
    NEW.created_at = currentTime;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_created_timestamp_for_customers
    BEFORE INSERT ON customers FOR EACH ROW
EXECUTE PROCEDURE set_created_timestamp();

CREATE TRIGGER trigger_set_created_timestamp_for_bills
    BEFORE INSERT ON bills FOR EACH ROW
EXECUTE PROCEDURE set_created_timestamp();

CREATE TRIGGER trigger_set_created_timestamp_for_receipts
    BEFORE INSERT ON receipts FOR EACH ROW
EXECUTE PROCEDURE set_created_timestamp();

CREATE TRIGGER trigger_set_created_timestamp_for_requests
    BEFORE INSERT ON requests FOR EACH ROW
EXECUTE PROCEDURE set_created_timestamp();
