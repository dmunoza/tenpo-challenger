export default interface Transaction {
    id: string;
    amount: number;
    commerce: string;
    user: string;
    dateTransaction: Date;
}