const db = require('../db')

module.exports = {
    recuperacaoDeTodos: () => {
        return new Promise((aceito, rejeitado) => {
            db.query('SELECT * FROM Acao', (error, results) => {
                if (error) { rejeitado(error); return; }
                aceito(results)
            })
        })
    },

    recuperacaoDeUm: (id) => {
        return new Promise((aceito, rejeitado) => {
            db.query('SELECT * FROM Acao WHERE id = ?', [id], (error, results) => {
                if (error) { rejeitado(error); return; }
                if (results.length > 0) {
                    aceito(results[0])
                } else {
                    aceito(false)
                }
            })
        })
    }
}