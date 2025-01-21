import { useState, useEffect } from "react";
import { addTransaction, updateTransaction } from "../services/api.ts";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import Transaction from "../interfaces/transactionsInterfaces.ts";
import { useQueryClient } from "@tanstack/react-query";
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
            {({ isSubmitting }) => (
                <Form>
                    <div>
                        <label>Monto:</label>
                        <Field type="number" name="amount" />
                        <ErrorMessage name="amount" component="div" />
                    </div>
                    <div>
                        <label>Comercio:</label>
                        <Field type="text" name="commerce" />
                        <ErrorMessage name="commerce" component="div" />
                    </div>
                    <div>
                        <label>Usuario:</label>
                        <Field type="text" name="user" />
                        <ErrorMessage name="user" component="div" />
                    </div>
                    <div>
                        <label>Fecha:</label>
                        <Field type="datetime-local" name="dateTransaction" />
                        <ErrorMessage name="dateTransaction" component="div" />
                    </div>
                    <button type="submit" disabled={isSubmitting}>
                        {isEditing ? "Editar Transacción" : "Agregar Transacción"}
                    </button>
                </Form>
            )}
        </Formik>
    );
};

export default TransactionForm;