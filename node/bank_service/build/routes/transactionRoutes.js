"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const transactionControllers_1 = __importDefault(require("../controllers/transactionControllers"));
class TransactionRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/transaction', transactionControllers_1.default.get);
        this.router.post('/transaction', transactionControllers_1.default.post);
    }
}
const transactionRoutes = new TransactionRoutes();
exports.default = transactionRoutes.router;
