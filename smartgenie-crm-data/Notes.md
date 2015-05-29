Notes
=====

Missing Fields
* Lead source - Retail only (text)
* Images Needed (boolean) [Done]
* Date added - free pick date default to today (date)
* State transition date for each transition free pick (date)
* Postcode - (text) [Done]

* Classification of Job - National Account, Retail, Trade (Colour to differentiate?)

Class specific fields
----------------------
* National accounts - Order No.
* Trade - Trader
		- Salesmans Name

* Job Status - new, contacted, booked, completed
state transition free of validation.


* Primary search - Reg no, Order Number (Nat account only)

* Field validations - fine as is

* List view what to show - Date entered, phone number, post code, price, status, name, reg number, damage description.

Roles
-----
User

Operations
----------
Add
Edit
Delete (with confirm)

Auth
----
open auth - Prob google id

Backup
------
Yes - Hourly?

Missing Features
----------------
Head office format email parser + email box

Openshift Credentials
---------------------
MySQL 5.5 database added.  Please make note of these credentials:

       Root User: adminZkPqZXs
   Root Password: kzkH3iyy3dCE
   Database Name: smartgenie

Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/