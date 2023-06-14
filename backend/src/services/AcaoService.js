const db = require('../db')

module.exports = {
    recuperacaoDeTodos: () =>{
        return new Promise((aceito, rejeitado)=>{
            db.query('SELECT * FROM Acao',(error, results)=>{
                if(error) {rejeitado(error); return;}
                aceito(results)
            })
        })
    }
}