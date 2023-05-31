# ProFlex - Group 13 in Application and Web development

> This is a university project for course IDATA2305 and IDATA2305

In this project we created a online web store which would sell an arrange of digital products. We accomplished this with a monolithic system design where we used spring boot (java) as the backend, and MySQL as the database system.

This project is hosted on a remote server given to us which runs on the domain:

[group13.web-tek.ninja](http://group13.web-tek.ninja)

this domain is only accessible through NTNU's networks.

## How to run

clone this repository, and run the application.

However this application is dependent on a MySQL database server hosted on your machine or a remote server which you have rights to.

I you already have a MySQL database you have to create a database called professionalwebsite, the application uses user and password as: root. 

> (if you want to change this you have to go into the spring.application file and change the SPRING_DATASOURCE_URL variable to correlate with your database.)

when you have set up the database and have filled in the necessary variables in spring.application it should run.
