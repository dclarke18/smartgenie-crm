#!/bin/bash

curl -i -X POST -H "Content-Type:application/json" -d '{ "orderId" : "REV-00003", "name" : "Dave Clarke", "address" : "no fixed abode", "emailAddress" : "dave.clarke@gmail.com", "telNo" : "07974 645920", "vehicleMakeAndModel" : "Toyota IQ", "vehicleReg" : "EO59 WXG", "damageDescription" : "Every panel scratched but not badly.", "price" : "125.00" }' http://localhost:8080/jobs
