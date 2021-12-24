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
        <#elseif denominations?? >
                    
            Notes Dispensed:<br>
            <#list denominations as denomination>
                <p>
                    Note: ${denomination.note}<br>
                    Quantity: ${denomination.quantity}<br>
                </p>
            </#list>
            <a href="/atm">Withdraw</a>
            <a href="/atm/acct">Account</a>
        <#else>
            <form action="/atm" method="post">
                <div>Account Number:</div>
                <input type="number" name="accountNumber" required/>
                <br/><br/>
                <div>PIN:</div>
                <input type="password" rows="2" name="pin" required/>
                <br/><br/>
                <div>Amount:</div>
                <input type="number" rows="2" name="amount" required/>
                <br/><br/>
                <input type="submit" value="Submit" name="submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        
    </body>
</html>
