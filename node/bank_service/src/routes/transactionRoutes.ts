import {Router} from 'express';
import transactionController from '../controllers/transactionControllers';

class TransactionRoutes {
    public router:Router;

    constructor() {
        this.router = Router();
        this.config();
    }

    config():void{
        this.router.get('/transaction', transactionController.get);
        this.router.post('/transaction',transactionController.post);
    }
}

const transactionRoutes = new TransactionRoutes();

export default transactionRoutes.router;