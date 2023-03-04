import { Router,Request,Response } from "express";
import RequestPostTransactionDto from "../models/transaction/RequestPostTransactionDto";
import ResponsePostTransactionDto from "../models/transaction/ResponsePostTransactionDto";
import pool from '../database';

class TransactionController {
    constructor() {
    }
    
    public get(req:Request, res:Response) {
        res.send('Hello from transaction get');
    }

    public async post(req:Request, res:Response):Promise<void> {
        const request:RequestPostTransactionDto = req.body;

        const queryAccount = `select * from account where idaccount = ?`;
        const account =  await pool.promise().query(queryAccount, [request.numero_cuenta]);
        console.log('account: ',account[0][0].idaccount)
        const queryOperation = `select * from operation_code where name = ?`;
        const operation =  await pool.promise().query(queryOperation, [request.operacion]);
        console.log('operation: ',operation[0][0].idoperation_code)

        const queryString = "INSERT INTO bank_database.`transaction` ( idaccount,idoperation_code,  amount, transaction_date, description)  values (?,?,?,NOW(),'description de la transaccion')";
        const transaction =  await pool.promise().query(queryString, [request.numero_cuenta, operation[0][0].idoperation_code, parseFloat(String(request.monto))]);

        const queryLastTransaction = `SELECT * FROM transaction t WHERE idtransaction  = (SELECT MAX(idtransaction) FROM transaction)`;
        const lastTransaction =  await pool.promise().query(queryLastTransaction);
        console.log('lastTransaction: ',lastTransaction[0][0].idtransaction)

        const result:ResponsePostTransactionDto = {
            codigo_respuesta: '0',
            descripcion_respuesta: 'OK',
            id_transaccion: lastTransaction[0][0].idtransaction
        }

        res.send(result);
    }
}


const transactionController = new TransactionController();
export default transactionController;