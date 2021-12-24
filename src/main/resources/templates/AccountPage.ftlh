<!DOCTYPE html>  
<html lang="en">  
    <head>
        <meta charset="UTF-8">
        <title>JBank ATM</title>
    </head>
    <body>
        <h1>Welcome to JBank ATM</h1>
        <#if errorMessage?? >
            <p style="color:red">${errorMessage}</p>
            <a href="/atm">Withdraw</a>
            <a href="/atm/acct">Account</a>
        <#elseif account?? >
            <p>
                Account details:<br>
                Account Number: ${account.accountNumber}<br>
                Available Banlance: ${account.balance}<br>
                Maximum Withdrawal Amount: ${account.balance + account.overDraft} 
                        (Account Balance ${account.balance} + Overdraft ${account.overDraft})<br>
                <a href="/atm">Withdraw</a>
                <a href="/atm/acct">Account</a>
            </p>
        <#else>
            <form action="/atm/acct" method="post">
                <div>Account Number:</div>
                <input type="number" name="accountNumber" required/>
                <br/><br/>
                <div>PIN:</div>
                <input type="password" rows="2" name="pin" required/>
                <br/><br/>
                <input type="submit" value="Submit" name="submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        
    </body>
</html>
