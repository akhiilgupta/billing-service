CREATE TABLE customers(
  	id bigserial primary key,
    name varchar(64) unique not null,
    email varchar(64) unique not null,
	display_name varchar(64) not null,
	phone varchar(13) not null,
	account_id varchar(64) unique not null,
	metadata jsonb,
	active boolean not null,
	created_at timestamp without time zone not null
);

create index customers_phone_idx on customers(phone);

CREATE TABLE bills(
  	id bigserial primary key,
    bill_id varchar(64) unique not null,
    status varchar(64) not null,
	customer_account varchar(64) not null references customers (account_id),
	bill_type varchar(64) not null,
	recurrence varchar(64) not null,
	amount_exactness varchar(64) not null,
	pending_amount int not null check (pending_amount >= 0),
	total_amount int not null check (total_amount > 0),
	metadata jsonb,
	due_date timestamp without time zone not null,
	created_at timestamp without time zone not null
);

create index bills_bill_id_idx on bills(bill_id);
create index bills_customer_account_idx on bills(customer_account);

CREATE TABLE receipts(
  	id bigserial primary key,
    receipt_id varchar(64) unique not null,
    biller_bill_id varchar(64) not null references bills (bill_id),
	platform_bill_id varchar(64) not null,
	amount int not null,
	bill_amount int not null,
	transaction_ref_Id varchar(64) not null,
	created_at timestamp without time zone not null
);

create index receipts_receipt_id_idx on receipts(receipt_id);
create index receipts_biller_bill_id_idx on receipts(biller_bill_id);

CREATE TABLE requests(
  	id bigserial primary key,
    unique_id varchar(64) unique not null,
    scheme_id varchar(64) not null,
    created_at timestamp without time zone not null
);

create index requests_unique_id_idx on requests(unique_id);