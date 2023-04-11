const express = require('express')
const app = express()

app.get('/', function (req, res) {
    console.log('connect /')
    res.send('Hello World !!')
})
app.listen(3333, function () {
    console.log('3333 port listen !!')
})