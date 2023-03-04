"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const database_1 = __importDefault(require("../database"));
class TransactionController {
    constructor() {
    }
    get(req, res) {
        res.send('Hello from transaction get');
    }
    post(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            const request = req.body;
            const queryAccount = `select * from account where idaccount = ?`;
            const account = yield database_1.default.promise().query(queryAccount, [request.numero_cuenta]);
            console.log('account: ', account[0][0].idaccount);
            const queryOperation = `select * from operation_code where name = ?`;
            const operation = yield database_1.default.promise().query(queryOperation, [request.operacion]);
            console.log('operation: ', operation[0][0].idoperation_code);
            const queryString = "INSERT INTO bank_database.`transaction` ( idaccount,idoperation_code,  amount, transaction_date, description)  values (?,?,?,NOW(),'description de la transaccion')";
            const transaction = yield database_1.default.promise().query(queryString, [request.numero_cuenta, operation[0][0].idoperation_code, parseFloat(String(request.monto))]);
            const queryLastTransaction = `SELECT * FROM transaction t WHERE idtransaction  = (SELECT MAX(idtransaction) FROM transaction)`;
            const lastTransaction = yield database_1.default.promise().query(queryLastTransaction);
            console.log('lastTransaction: ', lastTransaction[0][0].idtransaction);
            const result = {
                codigo_respuesta: '0',
                descripcion_respuesta: 'OK',
                id_transaccion: lastTransaction[0][0].idtransaction
            };
            res.send(result);
        });
    }
}
const transactionController = new TransactionController();
exports.default = transactionController;
