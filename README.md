
# Aspire Backend Code Challenge

It is an app that allows authenticated users to go through a loan application. It doesn’t have to contain too many fields, but at least “amount
required” and “loan term.” All the loans will be assumed to have a “weekly” repayment frequency.
After the loan is approved, the user must be able to submit the weekly loan repayments. It can be a simplified repay functionality, which won’t
need to check if the dates are correct but will just set the weekly amount to be repaid.

### Application Details
It uses H2 database, it is a RDBMS and can be used in house with java applications. In this application it gets bundled during the server start and is dropped once the server stops. Hence it holds only the current session data. H2 console can be accessed by http://localhost:9090/h2-console. Credentials for the console is available in application.properties file. The data is well abstracted from the implementation of services and util class are created using singleton pattern. The project is modularized are seperating the class in layers such as controllers, service , repository. All the test cases to individual classes are kept in test section to make it more readable and understandable.

1. The application runs on port 9090, hence use  http://localhost:9090/
2. Date format is not validated so use right date format YYYY-mm-DD 


### Folder Structure
```
loanbe  
└───src
    └───main/java
    │     └───com/aspire/loanbe
    │         └─── controller
    │         │    |  UserController.java    #Controller for user functions
    │         │    |  AdminController.java   #Controller for admin functions
    │         │    |  LoanController.java    #Controller for loan functions
    │         └─── dto                       #Request and response Objects 
    │         └─── exception                 #Custom defines exceptions   
    │         └─── message                   #All the custome defined messages         
    │         └─── model                     #All the entities defined here Ex: User etc 
    │         └─── repository                #All database related JPA function defined 
    │         └─── service                   #All the service related interfaces defined
    │         │     └───serviceImpl          #Implementation class for services defined here
    │         └─── util                      #Utility classes defined here         
    └───main/resources
    │      └───appplication.properties       #Properties defined Ex: DB details etc         
    └───test/java                            #Same replica as above with unit tests 
    │ 
    └───pom.xml                              #All the dependencies defined here
    └───ReadMe.md
```


## Database Tables
### Member
| Column |Description |
| :-------- |  :------------------------- |
| `ID` | Unique ID  |
| `USERNAME` | Name of the user |
| `ISADMIN` | Identifies as admin  |

### Loan
| Column |Description |
| :-------- |  :------------------------- |
| `ID` | Unique ID  |
| `AMOUNT` | Loan amount  |
| `TERM` | Number of installments  |
| `CUSTOMER_ID` | FK Which customer loan belongs |
| `STATE` | Status of loan |
| `LOAN_DATE` | Date of the loan |


### Scheduled Payment
| Column |Description |
| :-------- |  :------------------------- |
| `ID` | Unique ID  |
 `STATE` | Status of scheduled payemnt |
 | `AMOUNT` | Amount to be paid |
 | `LOAN_ID` | FK loan reference |
 | `REPAYMENT_DATE` | Date of repayment |

## API Reference

#### Create a User

```http
POST /api/v1/user
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Name of the user |
| `isAdmin` | `boolean` | Distinguishes from user/admin |

#### Get Loan
```http
  GET /api/v1/user/${id}/loans
```

#### Create Loan

```http
  POST /api/v1/user/${id}/loan
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `amount`      | `double` | **Required**. Total loan amount |
| `term`      | `int` | **Required**. Terms for payments |
| `date`      | `date` | **Required**. date from which payment is calculated |

#### Approve Loan

```http
POST /api/v1/admin/${id}/approve
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `loanId` | `int` | **Required**. Should be a valid loan |


#### Scheduled Repayment

```http
POST /api/v1/${id}/repay
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `loanId` | `int` | **Required**. Should be a valid loan |
| `amount` | `double` | **Required**.Should be greater than or equal to scheduled payment amount|

### Avaiable endpoints in localhost
localhost:9090/api/v1/user 

localhost:9090/user/{id}/loan 

localhost:9090/user/{id}/loans

localhost:9090/admin/{id}/approve

localhost:9090/user/{id}/repay

## Running the application

### Prerequisites

Before running the application, ensure you have the following dependencies installed:
#### Java Development Kit (JDK):[ Link to Download](https://www.oracle.com/in/java/technologies/javase/javase8-archive-downloads.html)
#### Maven:  [ Link to Download](https://maven.apache.org/install.html)
#### Your preferred IDE (e.g.IntelliJ IDEA, Eclipse): [ Link to Download](https://spring.io/tools/) 

#### 1. Clone the repository 
```bash
  git clone {url}
```
#### 2.Switch to master branch
```bash
  git switch master
```
#### 3. Build the project
Downloads all the dependencies provided in pom.xml and builds the project
```bash
  mvn clean install
```
#### 4. Run the application
```bash
  mvn spring-boot:run
```





