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
class AccountController {
    constructor() {
    }
    get(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            const queryString = `
        select a.idaccount as numero_cuenta ,
        CONCAT(c.first_name,' ',c.middle_name,' ',c.last_name ,' ',c.second_last_name )as nombre_cliente,
        tp.name as tipo_cuenta,
        p.interest_rate as tasa_interes,
        a.balance as saldo,
        s.name as estatus
        from account a 
        inner join client c on c.idclient = a.idclient  
        inner join product p ON p.idproduct = a.idproduct 
        inner join type_product tp on tp.idtype_account = p.idtype_account 
        inner join status s on s.idstatus = a.idstatus ;
        `;
            const accountBalance = yield database_1.default.promise().query(queryString);
            res.json(accountBalance[0]);
        });
    }
}
const accountController = new AccountController();
exports.default = accountController;
