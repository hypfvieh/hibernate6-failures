# Hibernate 6 failures
Sample code to demonstrate various bugs in Hibernate 6.x.

Each sample has its own branch, so please take a look at the tags.

# Current bugs:

Hibernate 6.0.0.Final:
	- Exception when using empty list/collection for 'IN' statement query ([HHH-15232](https://hibernate.atlassian.net/browse/HHH-15232), fixed in 6.0.1, branch: 6.0.0.Final-empty-collection-fail)

Hibernate 6.0.1.Final:
	- Exception when using e.g. 'LOWER()/UPPER()' function on CLOB fields ([HHH-TODO](https://hibernate.atlassian.net/browse/HHH-TODO), branch: 6.0.1.Final-function-on-clob-fail)
