export default{
    database:{
        host: process.env.BANK_DATABASE_HOST||'localhost',
        port: Number(process.env.BANK_DATABASE_PORT)||3306,
        user: process.env.MYSQL_USERNAME||'root',
        password: process.env.MYSQL_PASSWORD||'123456',
        database: 'bank_database',
    }
}