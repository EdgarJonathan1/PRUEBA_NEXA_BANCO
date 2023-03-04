import {Router} from 'express';
import accountController from '../controllers/accountControllers';

class AccountRoutes {

    public router:Router;

    constructor() {
        this.router = Router();
        this.config();
    }

    config():void{
        this.router.get('/account-balance', accountController.get);
    }
}

const accountRoutes = new AccountRoutes();

export default accountRoutes.router;