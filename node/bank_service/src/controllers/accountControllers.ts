import { Router, Request, Response } from "express";
import pool from '../database';

class AccountController {
    constructor() {
    }

    public async get(req: Request, res: Response):Promise<void> {
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

        const accountBalance =  await pool.promise().query(queryString);
        res.json(accountBalance[0]);
    }

}


const accountController = new AccountController();
export default accountController;