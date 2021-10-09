# StarWallet-Backend

Name - Star Wallet

Tech Stack  - Java, Spring-Boot, JPA, H2, Mockito

Description - Rest API collection for backend architecture of a real world wallet.

- User registers as Merchant/Customer.
- User creates a new BUSINESS(Merchant only) or PERSONAL(Merchant/Customer) wallet.
- Only PERSONAL wallets are allowed to make transactions, therefore PERSONAL wallet is recharged with some amount in wallet.
- User uses the funds available in their wallet to make transactions to other BUSINESS/PERSONAL wallets.


---

User Types:
* Merchant - Can receive funds from Customers or withdraw Payments from their BUSINESS wallets into their PERSONAL wallets.
* Customer - Can recharge wallets and use the funds to send to other Customers or Merchants.

Wallet Types:
- BUSINESS - Can be used to receive or withdraw funds.
- PERSONAL - Can be used to pay and receive funds.

