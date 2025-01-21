import {useState} from 'react'
import './App.css'
import TransactionForm from "./components/TransactionForm.tsx";
import TransactionsTable from "./components/TransactionTable.tsx";
import Transaction from "./interfaces/transactionsInterfaces.ts";

function App() {
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);

    const handleTransactionAdded = (newTransaction: Transaction) => {
        setTransactions([...transactions, newTransaction]);
    };
    const handleEditTransaction = (transaction: Transaction) => {
        setSelectedTransaction(transaction);
    };
  return (
      <>
          <h2>Transacciones</h2>
          <TransactionForm onTransactionAdded={handleTransactionAdded} selectedTransaction={selectedTransaction}/>
          <TransactionsTable onEditTransaction={handleEditTransaction}/>
      </>
  )
}

export default App
