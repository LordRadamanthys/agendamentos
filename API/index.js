const BodyParser = require('body-parser')
const Express = require('express')
const router = require('./controllers/router')
const routerAuth = require('./controllers/routerAuth')


const app = Express()

app.use(BodyParser.json())
app.use(BodyParser.urlencoded({extend:false}))
app.use(router)
app.use(routerAuth)




app.listen(3030)