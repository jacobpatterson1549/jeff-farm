# jeff-farm
a web server/site to track the farm

# Deployment
* Deployed on heroku at jeff-farm.herokuapp.com
* The ui (jeff-farm-ui) and webservice (jeff-farm-ws) are actually deployed on different heroku apps.  The webservice is deployed at jeff-farm-ws.herokuapp.com.


heroku create jeff-farm-ws --remote heroku-backend
git subtree push --prefix jeff-farm-ws/ heroku-backend master
heroku addons:create heroku-postgresql


## License
Project is licensed under the [MIT license](LICENSE.md).
