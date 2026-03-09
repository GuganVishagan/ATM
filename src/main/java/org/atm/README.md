Functional : 

- Withdraw Cash
- Card Authentication
- Balance Enquiry
- Deposit cash ( User )
- Deposit cash ( Admin )
- Balance check ( Admin )



Core Entities 

ATM
Card
Account
Transaction



ATM 
- authenticateUser()
- startTransaction()
- depositAdminCash()
- checkAdminBalance()

Account
- checkBalance()
- debit()
- credit()

Transaction()
-execute()



WithdrawTransaction and DepositTransaction will implement the interface transaction


