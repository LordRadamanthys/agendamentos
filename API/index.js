const BodyParser = require('body-parser')
const Express = require('express')
const router = require('./controllers/router')
const routerAuth = require('./controllers/routerAuth')
//git rm -r --cached .

const app = Express()

app.use(BodyParser.json())
app.use(BodyParser.urlencoded({extend:true}))
app.use(router)
app.use(routerAuth)




app.listen(3333)