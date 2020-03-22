
const express = require('express')
const http = require('http')
const app = express()
var userNicknameb
const server = http.createServer(app)
const io = require('socket.io').listen(server)

app.get('/', (req, res) => {

    res.send('Chat Server is running on port 3000')
})


io.on('connection', (socket) => {

    console.log('user connected')

    socket.on('join', function (userNickname) {

        console.log(userNickname + " : has joined the chat ");
        userNicknameb = userNickname

        socket.emit('userjoinedthechat', userNickname + " : has   joined the chat ");
    })


    socket.on('messagedetection', (senderNickname, messageContent) => {

        //log the message in console 

        console.log(senderNickname + " : " + messageContent)

        //create a message object 

        let message = {
            "message": messageContent,
            "senderNickname": senderNickname
        }

        // send the message to all users including the sender  using io.emit  
        //console.log(message)
        return io.emit('message', message)

    })

    socket.on('disconnect', function () {

        console.log(userNicknameb + ' has left ')

        socket.broadcast.emit("userdisconnect", ' user has left')

    })

})


server.listen(3000, () => {

    console.log('Node app is running on port 3000')

})