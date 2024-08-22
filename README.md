**Environment Setup**

Java 17
MySql
postman for testing

**clone the project**

https://github.com/piratharsanna/dbs-git-get-repo.git

**DB Setup**

set DB user-name and passowrd in application.properties file
run the sql query to create the schema and table,  CreateDBQuery.sql available in resources folder

**Run the application**

Make sure your ide set already java & maven and other access.(Prefer Intellij)
run the command "mvn clean install"
Easy way go to main class (DbsGitGetRepoApplication.java) and play the run button for first time , later can use ide run btn

**Test Application**

**Sample Request**

http://localhost:8080/repositories/piratharsanna/dbs-git-get-repo

**Response**

{
    "success": true,
    "message": "Success",
    "data": {
        "id": 4,
        "repoName": "dbs-git-get-repo",
        "ownerName": "piratharsanna",
        "description": null,
        "cloneUrl": "https://github.com/piratharsanna/dbs-git-get-repo.git",
        "stars": 0,
        "createdAt": "2024-08-22T15:02:02"
    }
}


<img width="946" alt="image" src="https://github.com/user-attachments/assets/eebaded6-e95e-430a-b992-c0b45d9880a6">

**Or Else Run Jar file**

use "mvn clean install" and coppy jar from target folder and copy application.properties file and past both in same folder then run the jar file.


**Response & Exception Handling**

Using Generic response , it will help full if we using front-end in future
Controller-Advice using for exception handling

**Security**

Security not implemented , this release not required 


**Application Behaviour**

When Query the details by uasing ownerName & repository and if availabe in the internet then it save that data in to Db and response back the same data
the query data prsent in the db then it will not checking in internet , it will fetch from db & response back very fast
if try wrong data qury then it will throw handled ecpetion with message


