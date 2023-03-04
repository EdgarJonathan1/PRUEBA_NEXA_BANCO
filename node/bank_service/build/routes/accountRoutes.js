"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const accountControllers_1 = __importDefault(require("../controllers/accountControllers"));
class AccountRoutes {
    constructor() {
        this.router = (0, express_1.Router)();
        this.config();
    }
    config() {
        this.router.get('/account-balance', accountControllers_1.default.get);
    }
}
const accountRoutes = new AccountRoutes();
exports.default = accountRoutes.router;
