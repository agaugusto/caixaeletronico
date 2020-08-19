This API was developed to meet the challenge of dojopuzzles.com about an ATM.
Below are the rules for the dojo.

Develop a program that simulates the delivery of money when a customer withdraws at an ATM. The requirements
basic are as follows:

Deliver the smallest number of cash notes;
It is possible to withdraw the requested amount with the available cash notes;
Infinite customer balance;
Infinite banknote quantity (you can place a finite amount of banknote to increase the difficulty of the problem);
Available cash note of R$ 100.00; R$ 50.00; R$ 20.00 and R$ 10.00

Instructions for use.

Run the ATM project in your IDE.
Configure Postman or any other RESTFul tool to GET at the address
localhost:8080/withdraw/:value
Inform the amount to be withdrawn, only whole amounts are allowed.
When executing, if money is available, a Json will be returned informing the note and quantity.
If you have no more money, an exception will be returned.

Initial amount of money in the dispenser.
R$ 100.00 - 5
R$ 50.00 - 7
R$ 20.00 - 2
R$ 10.00 - 5