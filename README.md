# RateCalculation
Rate calculation system allowing prospective borrowers to obtain a quote from a pool of lenders for 36 month loans.

### Theoretical analysis and assumptions

Taking in consideration a pull of lenders, a request of loan may be met for a total amount which does not exceed the sum of available funds of lenders.

The requested amount will be provided starting from the lender with lowest rate, therefore initially the pull is being sorted by their rate.

For every amount provided by the lenders is being calculated the monthly repayment. Since the lender's rate is an annual rate, it is turned into a monthly effective rate:

```
a = annual rate
r = monthly effective rate

r = (1 + a)^(1/12) - 1
```

Therefore, with:

```
s = value of loan
m = monthly repayment
r = monthly effective rate
n = number of month loans

m = (r s)/(1 - (1 + r)^-n)
```

The sum of monthly repayments is going to be the effective monthly repayment of the borrower, then the total repayment is the product of the effective monthly repayment with the number of month loans.

```
n = number of month loans
c = number of lenders who contribute to the loan
d = effective monthly repayment = m1 + m2 + ... + mc
t = total repayment

t = d n
```

To calculate the rate of the loan, it is used the constant-ratio method which take in consideration the interest:

```
t = total repayment
s = value of loan
i = interest

i = t - s
```

In conclusion, with:

```
f = number of payment periods per year, i.e. 12
i = interest
s = value of loan
n = number of month loans
g = rate of the loan

g = 2 f i / (s (n + 1))
```

#### External links

[https://en.wikipedia.org/wiki/Effective_interest_rate](https://en.wikipedia.org/wiki/Effective_interest_rate) Effective interest rate

[http://www.financeformulas.net/Loan_Payment_Formula.html](http://www.financeformulas.net/Loan_Payment_Formula.html) Loan payment

[http://simplestudies.com/effective-interest-rate-in-context-of-loans.html/page/2](http://simplestudies.com/effective-interest-rate-in-context-of-loans.html/page/2) Effective annual interest rate on installment loans

### Installing

Since the request was a command-line application, the system has been developed without framework in order to have an application light and fast. To take the compiled code and package it in its distributable format, in this case a JAR:

```
mvn package
```

#### Running tests

With a coverage over 90%, tests have been produced following the process of TDD. Files market.csv, market.txt and marketFull.csv have been used for the sake of tests.

To run unit tests only:

```
mvn test
```

#### Running the application

To run the application from command line:

```
java -jar target/RateCalculation-jar-with-dependencies.jar
```