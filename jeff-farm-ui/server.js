var express = require('express');
var path = require('path');

var app = express();

// Serve only the static files form the dist directory
var app_name = 'jeff-farm-ui'
app.use(express.static(path.join(__dirname, 'dist', app_name)));
app.get('/*', function (_req, res) {
    res.sendFile(path.join(__dirname, 'dist', app_name, 'index.html'));
});

// Initialize the app.
var server = app.listen(process.env.PORT || 4200, function () {
    var port = server.address().port;
    console.log('App now running on port', port);
});

if (process.env.NODE_ENV === 'production') {
    app.use(function (req, res, next) {
        return req.secure
            ? next()
            : res.redirect('https://' + req.headers.host + req.path);
    });
}
