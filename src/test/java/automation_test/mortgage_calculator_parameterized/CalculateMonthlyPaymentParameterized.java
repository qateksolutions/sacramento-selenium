package automation_test.mortgage_calculator_parameterized;

import automation_test.BaseClass;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.DateUtils;
import utilities.SqlConnector;

import java.sql.ResultSet;

public class CalculateMonthlyPaymentParameterized extends BaseClass {
    @Test
    public void calculateMonthlyPayment() throws Exception {
        String[] date = DateUtils.returnNextMonth();
        String month = date[0];
        String year = date[1];

        ResultSet rs = SqlConnector.readDate("select * from monthly_mortgage");
        while (rs.next()) {
            new Home(driver)
                    .typeHomePrice(rs.getString("homevalue"))
                    .typeDownPayment(rs.getString("downpayment"))
                    .clickDownPaymentInDollar()
                    .typeLoanAmount(rs.getString("loanamount"))
                    .typeInterestRate(rs.getString("interestrate"))
                    .typeLoanTermYears(rs.getString("loanterm"))
                    .selectMonth(month).typeYear(year)
                    .typePropertyTax(rs.getString("propertytax"))
                    .typePMI(rs.getString("pmi"))
                    .typeHomeOwnerInsurance(rs.getString("homeownerinsurance"))
                    .typeMonthlyHOA(rs.getString("monthlyhoa"))
                    .selectLoanType(rs.getString("loantype"))
                    .selectBuyOrRefinance(rs.getString("buyorrefi"))
                    .clickOnCalculateButton()
                    .validateTotalMonthlyPayment(rs.getString("totalmonthlypayment"));
        }
    }
}
