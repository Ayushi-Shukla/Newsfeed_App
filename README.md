# Introduction
This is a boiler plate project for FSD Certification Practice Check. Admin can search and blacklist a news analyst. A logged in user can search for news. For each user, the system maintains the historical list of keywords searched. In the home screen the news related to the search keywords are displayed under relevant headings. Users can view the topics searched and can delete the searched topics. Fork this project and create the below specified issues in the forked project.

| **Issue Title** | **Issue Description** |
|-----------|-------------------|
| 1. Implement the functionality of the application | Create a website for users to signup and search new articles in a lively manner. Refer detailed description below this table. |
| 2. Analysis and Design | Define Screen Layout, ER Diagram, Classes and Method signatures. Include the documentation in README.md section of the project. |
| 3. Implement Repository and Service Layer | Create database and implement Service Layer using Hibernate. Unit Testing of the service method should be done using Mockito. Document the steps to build, unit test and deploy in Wiki. |
| 4. Implement Rest Controller | Create the Restful Web Service Controller using Spring MVC and create end to end tests using MockMvc library available in Spring. Document the steps to build, unit test and deploy. |
| 5. Implement Authentication Service | Modify the test cases based on inclusion of Authentication. |
| 6. Implement CI/CD | Automate the deployment of WAR using Jenkins. |
| 7. Implement Front End and consume Rest Services | Implement front end using Angular with responsive web design. Implement Unit Testing using Karma. Implement end to end testing with Protractor. |
| 8. Document the steps for build and deployment | Create a subheading for this in README.md and include the steps to deploy. |
| 9. Create docker compose for this application | |

# Application Functionality in detail (Include this in issue description of forked project)

## Role: News Analyst
1. Signup
2. Login
3. Search news articles after login (use https://newsapi.org to retrieve live new data.)
4. View the news related to the topics in search topic
5. List the searched topics
6. Remove topics from the search list
7. Logout

## Role: Admin
1. Login
2. Search News Analyst
3. Blacklist News Analyst
4. Logout




This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.0.6.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/)

