export default interface ResponseGetTransactionDto{

    id_transaccion: number,
    numero_cuenta: number,
    nombre_cliente: string,
    fecha_hora: Date,
    monto: number,
    operacion: string,
    descripcion: string,
}