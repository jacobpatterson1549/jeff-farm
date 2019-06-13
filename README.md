# jeff-farm
a website to track farm efficiency

# Deployment
* Deployed on heroku at https://jeff-farm.herokuapp.com
* The ui (jeff-farm-ui) and webservice (jeff-farm-ws) are actually deployed on different Heroku apps.  The webservice is deployed at jeff-farm-ws.herokuapp.com.
* Because the ui and webservice are in one repository, git-subtree must be used to deploy the apps separately:
`git subtree push --prefix jeff-farm-ws/ heroku-backend master` and `git subtree push --prefix jeff-farm-ui/ heroku master`

* The heroku Postgresql addon is used to run liquibase to set up and apply changes to the database.  It was added to jeff-farm-ws with `heroku addons:create heroku-postgresql`.

## Third-Party Technologies
* Angular 2+ frontend
* Apache Tomcat backend (embedded)
* Postgresql database
* Glassfish Jersey REST web services
* HK2 dependency injection
* Yasson javax.json serialization
* Junit tests
* Mockito object mocking test framework
* Grizzly web server for tests


## License
Project is licensed under the [MIT license](LICENSE.md).
