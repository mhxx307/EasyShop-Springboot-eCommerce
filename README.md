# EasyShop

## - Following technologies:

-   Spring framework development with Spring Boot
-   Spring Data JPA and Hibernate framework
-   Spring Security for authentication and authorization
-   Spring OAuth for social login with Facebook and Google
-   Spring Mail for sending customer registration confirmation and order confirmation
-   PayPal Checkout API for capturing payment from customer
-   Google Chart API for drawing charts of sales report
-   JUnit, AssertJ and Mockito in unit testing
-   Spring RESTful Webservices
-   Bootstrap 4, HTML5 and jQuery 3
-   Amazon S3 APIs
-   Deployment on Heroku (AWS)

## Understand functional requirements

### Key actors:

-   Back end: Admin, editor, Salesperson, Shipper, Assistant
-   Front end: Visitor, customer
    Actors usecase:
-   Admin: manage everything
-   Sales person: Manage product price,customers, shipping, orders and sales report
-   Editor: Manage categories, brands, products, articles and menus
-   Shipper: view products, view orders and update order status
-   Assistant: manage question and reviews

### Overview

![overview](https://github.com/mhxx307/EasyShop/assets/69964747/470de432-627e-49e3-9fa3-ae64d1a184f8)

## Understand technical requirements

-   Accessibility: App can be accessible from any devices connected to the internet: PC, laptop, tablet, smartphone...
-   Availability: User can access apps any time, 24/7
-   Security: Authentication required for all users (except visitors), Authorization required in the admin CP(editor, salesperson, shipper,...)
-   Performance: Fast response time, No request take longer than 4 seconds
-   Scalebility: App can be scaled on demand and running well under loads, Back-end and Front-end apps can be scaled separated

## Understand UI requirements

-   Intuitive - nice - easy to use
-   Responsive - look good on different devices

### make an module maven project

![multi_project](https://github.com/mhxx307/EasyShop/assets/69964747/b2fbdf22-ff10-4fc4-9e28-606e0621bd66)

### Architecture

![Architecture drawio](https://github.com/mhxx307/EasyShop/assets/69964747/37c89d90-3b15-4b69-b51b-7f8e33781056)

### make database

image here (update later)

### make user module

![user-module](https://github.com/mhxx307/EasyShop/assets/69964747/b139a36f-1261-4742-a127-bcb813913e7e)

### Upload photo requirement:

-   User's photo is optional
-   Image files store in the file system
-   Image file names are stored in the database
-   Show thumbnail image in user form
-   File size < 1MB
-   In edit mode: delete old photos
-   In production on Heroku, AWS or whatever cloud service: photos will be uploaded to Amazon S3 server

### Code user authentication

image here (upload later)
