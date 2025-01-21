import React  from 'react';
import {useQuery} from "@tanstack/react-query";
import {deleteTransaction, fetchTransactions} from "../services/api.ts";
import Transaction from "../interfaces/transactionsInterfaces.ts";
import moment from "moment";
import queryClient from "../queryClient.ts";
interface TransactionsTableProps {
    onEditTransaction: (transaction: Transaction) => void;
}

const TransactionsTable: React.FC<TransactionsTableProps> = ({ onEditTransaction }) => {
    const { data, isLoading, isError } = useQuery({queryKey: ["transactions"], queryFn: fetchTransactions})
    console.log(data);
    if (isLoading) return <p>Cargando transacciones...</p>;
    if (isError) return <p>Error al cargar las transacciones</p>;
    const onDeleteTransaction = async (id: string) => {
        await deleteTransaction(id);
        await queryClient.invalidateQueries({ queryKey: ["transactions"] });
    }
    return (
        <table border={1}>
            <thead>
            <tr>
                <th>ID</th>
                <th>Monto</th>
                <th>Comercio</th>
                <th>Usuario</th>
                <th>Fecha</th>
            </tr>
            </thead>
            <tbody>
            {data.map((tx: any) => (
                <tr key={tx.id}>
                    <td>{tx.id}</td>
                    <td>{tx.amount}</td>
                    <td>{tx.commerce}</td>
                    <td>{tx.user}</td>
                    <td>{moment(tx.dateTransaction).format("YYYY-MM-DD HH:mm")}</td>
                    <td>
                        <button onClick={() => onEditTransaction(tx)}>Editar</button>
                    </td>
                    <td>
                        <button onClick={() => onDeleteTransaction(tx.id)}>Eliminar</button>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default TransactionsTable;