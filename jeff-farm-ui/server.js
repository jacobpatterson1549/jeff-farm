const express = require('express');
const path = require('path');

const app = express();

// Serve only the static files form the dist directory
const app_name = 'jeff-farm-ui'
app.use(express.static(path.join(__dirname, 'dist', app_name)));
app.get('/*', function (_req, res) {
    res.sendFile(path.join(__dirname, 'dist', app_name, 'index.html'));
});

// Initialize the app.
const server = app.listen(process.env.PORT || 4200, function () {
    const port = server.address().port;
    console.log('App now running on port', port);
});

if (process.env.NODE_ENV === 'production') {
    app.use(function (req, res, next) {
        return req.secure
            ? next()
            : res.redirect('https://' + req.headers.host + req.path);
    });
}
