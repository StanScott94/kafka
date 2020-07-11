# Playing with kafka

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### INFO: 

	kafka-phone-call:
	kafka-phone-call produces phonecalls:
		- random userId (random String between "user1" - "user5")
		- random duration (random long between 0 - 299)
		- random data (random long between 0 - 699

		phonecalls are pushed to a topic configured in application.yml
			- topic-name: advice-topic

	kafka-phone-call-cost-processor:
		consumes and calculates users phone call:
			- duration * ratePerSecond
			- data * ratePerMB
		stores user, call and cost detail in a database:
			- database connection and signin configured in application.yml
		rates are configured in application.yml
			- rate-per-second: 0.1
			- rate-per-mb: 0.05

	docker-compose.yml
		- creates a zookeeper instance
		- creates a kafka instance
		- creates a MySQL database instance

### Deployment:

	1) start docker

	2) start cluster: 
		single broker:
			docker-compose up -d

	3) run applications:
		a) build kafka-phone-call:
			-mvn clean package
		b) start one or multiple instances (increaseing port number for each)
			-java -jar target\kafka-phone-call-0.0.1-SNAPSHOT.jar --server.port=9090

		a) build kafka-phone-call-cost-processor:
			-mvn clean packagejava
		b) start one or multiple instances (increaseing port number for each)
			-java -jar target\kafka-phone-call-cost-processor-0.0.1-SNAPSHOT.jar --server.port=8080

