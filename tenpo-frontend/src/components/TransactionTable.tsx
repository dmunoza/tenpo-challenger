import React  from 'react';
import {useQuery} from "@tanstack/react-query";
import {deleteTransaction, fetchTransactions} from "../services/api.ts";
import Transaction from "../interfaces/transactionsInterfaces.ts";
import moment from "moment";
import queryClient from "../queryClient.ts";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button } from '@mui/material';
interface TransactionsTableProps {
    onEditTransaction: (transaction: Transaction) => void;
}

const TransactionsTable: React.FC<TransactionsTableProps> = ({ onEditTransaction }) => {
    const { data, isLoading, isError } = useQuery({queryKey: ["transactions"], queryFn: fetchTransactions})
    if (isLoading) return <p>Cargando transacciones...</p>;
    if (isError) return <p>Error al cargar las transacciones</p>;
    const onDeleteTransaction = async (id: string) => {
        await deleteTransaction(id);
        await queryClient.invalidateQueries({ queryKey: ["transactions"] });
    }
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Amount</TableCell>
                        <TableCell>Commerce</TableCell>
                        <TableCell>User</TableCell>
                        <TableCell>Date</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((tx: Transaction) => (
                        <TableRow key={tx.id}>
                            <TableCell>{tx.amount}</TableCell>
                            <TableCell>{tx.commerce}</TableCell>
                            <TableCell>{tx.user}</TableCell>
                            <TableCell>{moment(tx.dateTransaction).format("YYYY-MM-DD HH:mm")}</TableCell>
                            <TableCell>
                                <Button onClick={() => onEditTransaction(tx)}>Edit</Button>
                                <Button onClick={() => onDeleteTransaction(tx.id)}>Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default TransactionsTable;