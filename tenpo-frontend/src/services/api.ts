import axios from "axios";
import Transaction from "../interfaces/transactionsInterfaces.ts";

const api = axios.create({
    baseURL: "http://localhost:8080/transaction",
});

export const fetchTransactions = async () => {
    const { data } = await api.get("/findAll")
    return data;
};

export const addTransaction = async (transaction: {
    amount?: number;
    commerce?: string;
    user?: string;
    dateTransaction?: Date
}) => {
    const { data } = await api.post("/", transaction);
    return data;
};

export const updateTransaction = async (transaction: Transaction) => {
    const body = {
        ...transaction
    }
    const { data } = await api.put(`/`, body);
    return data;
};

export const deleteTransaction = async (id: string) => {
    const { data } = await api.delete(`/${id}`);
    return data;
};

export default api;