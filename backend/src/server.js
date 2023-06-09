require('dotenv').config({path:'variaveis.env'})
const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')

const routes = require('./routes')

const server = express()

server.use(cors())
server.use(bodyParser.urlencoded({extended:false}))
server.use(express.json())

server.use((req, res, next) => {
    console.time("request")
    console.log("started request using "+req.method+" in "+req.url)
    next()
    console.log("finished request in "+req.url)
    console.timeEnd("request")
})

server.use('/api', routes)

server.listen(process.env.PORT, '0.0.0.0' ,()=>{
    console.log(`Servidor rodando em: http://localhost:${process.env.PORT}`)
})  