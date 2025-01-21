import { useState, useEffect } from "react";
import { addTransaction, updateTransaction } from "../services/api.ts";
import { Formik, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import Transaction from "../interfaces/transactionsInterfaces.ts";
import { useQueryClient } from "@tanstack/react-query";
import { TextField, Button, Grid2, Paper } from '@mui/material';
interface TransactionFormProps {
    onTransactionAdded: (transaction: Transaction) => void;
    selectedTransaction: Transaction | null;
}

const TransactionForm: React.FC<TransactionFormProps> = ({ onTransactionAdded, selectedTransaction }) => {
    const [transaction, setTransaction] = useState<Transaction>({
        id: "",
        amount: 0,
        commerce: "",
        user: "",
        dateTransaction: new Date(),
    });
    const [isEditing, setIsEditing] = useState(false);
    const queryClient = useQueryClient();

    const validationSchema = Yup.object({
        amount: Yup.number().required("Monto es requerido").positive("Monto debe ser positivo"),
        commerce: Yup.string().required("Comercio es requerido"),
        user: Yup.string().required("Usuario es requerido"),
        dateTransaction: Yup.date()
            .required("Fecha es requerida")
            .max(new Date(), "La fecha no puede ser superior a la fecha y hora actual"),
    });

    useEffect(() => {
        if (selectedTransaction) {
            setTransaction(selectedTransaction);
            setIsEditing(true);
        }
    }, [selectedTransaction]);

    const handleSubmit = async (values: Transaction, { setSubmitting, resetForm }: any) => {
        try {
            if (selectedTransaction) {
                const response = await updateTransaction(values.id, values);
                onTransactionAdded(response.data);
            } else {
                const response = await addTransaction(values);
                onTransactionAdded(response.data);
            }
            resetForm();
            setTransaction({id: "",
                amount: 0,
                commerce: "",
                user: "",
                dateTransaction: new Date()});
            await queryClient.invalidateQueries({ queryKey: ["transactions"] });
        } catch (error) {
            console.error("Error al agregar o editar la transacción:", error);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <Formik
            initialValues={transaction}
            validationSchema={validationSchema}
            onSubmit={handleSubmit}
            enableReinitialize
        >
            {({ isSubmitting, handleChange, values }) => (
                <Form>
                    <Paper style={{ padding: 16 }}>
                        <Grid2 container spacing={2}>
                            <Grid2 item xs={15}>
                                <TextField
                                    fullWidth
                                    label="Amount"
                                    name="amount"
                                    type="number"
                                    value={values.amount}
                                    onChange={handleChange}
                                    error={!!(values.amount && values.amount <= 0)}
                                    helperText={<ErrorMessage name="amount" />}
                                />
                            </Grid2>
                            <Grid2 item xs={15}>
                                <TextField
                                    fullWidth
                                    label="Commerce"
                                    name="commerce"
                                    value={values.commerce}
                                    onChange={handleChange}
                                    error={!!(values.commerce && values.commerce === "")}
                                    helperText={<ErrorMessage name="commerce" />}
                                />
                            </Grid2>
                            <Grid2 item xs={15}>
                                <TextField
                                    fullWidth
                                    label="User"
                                    name="user"
                                    value={values.user}
                                    onChange={handleChange}
                                    error={!!(values.user && values.user === "")}
                                    helperText={<ErrorMessage name="user" />}
                                />
                            </Grid2>
                            <Grid2 item xs={15}>
                                <TextField
                                    fullWidth
                                    label="Date"
                                    name="dateTransaction"
                                    type="datetime-local"
                                    value={values.dateTransaction}
                                    onChange={handleChange}
                                    error={!!(values.dateTransaction && new Date(values.dateTransaction) > new Date())}
                                    helperText={<ErrorMessage name="dateTransaction" />}
                                />
                            </Grid2>
                            <Grid2 item xs={15}>
                                <Button type="submit" variant="contained" color="primary" disabled={isSubmitting}>
                                    {isEditing ? "Edit Transaction" : "Add Transaction"}
                                </Button>
                            </Grid2>
                        </Grid2>
                    </Paper>
                </Form>
            )}
        </Formik>
    );
};

export default TransactionForm;