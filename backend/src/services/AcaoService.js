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
    },

    inclusao: (nome, descricao, tipo) => {
        return new Promise((aceito, rejeitado) => {

            db.query('INSERT INTO Acao (nome, descricao, tipo) VALUES (?, ?, ?);',
                [nome, descricao, tipo],
                (error, results) => {
                    if (error) {
                        console.log(error);
                        rejeitado(error);
                        return;
                    }
                    aceito(results.insertId)
                })
        })
    },

    atualizacao: (id, nome, descricao, tipo) => {
        return new Promise((aceito, rejeitado) => {

            db.query('UPDATE Acao SET nome = ?, descricao = ?, tipo = ? WHERE id = ?',
                [nome, descricao, tipo, id],
                (error, results) => {
                    if (error) {
                        console.log(error);
                        rejeitado(error);
                        return;
                    }
                    aceito(results)
                })
        })
    }
}