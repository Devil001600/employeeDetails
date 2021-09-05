CREATE TABLE employee_details (
    commit_id VARCHAR(08) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    type VARCHAR(15),
    cost_bill_link VARCHAR(25) unique,
    cost_settle_link VARCHAR(25) unique,
    designation VARCHAR(30) null,
	team VARCHAR(15) null,
	line_of_business VARCHAR(20) null,
	email_id VARCHAR(120) null,
	phone VARCHAR(20) null,
	status VARCHAR(10) null,
	date_of_joining DATE null,
	last_working_date DATE null
);