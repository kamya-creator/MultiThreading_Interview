package org.example.StampedLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class IncomeTaxClient {

    public static final Logger LOG = Logger.getLogger(String.valueOf(IncomeTaxClient.class));
    private static final int NUMBER_OF_TAX_PAYER = 1000;
    private static final IncomeTaxDept incomeTaxDept = new IncomeTaxDept(1000, NUMBER_OF_TAX_PAYER);
    public static void main(String[] args) {

        registerTaxPayers();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        LOG.info("Initial Income Tax Department Total Revenue is >>>>>>>>>>>>>>>>>>" + incomeTaxDept.getTotalRevenue());

        for(TaxPayer taxPayer : incomeTaxDept.getTaxPayersList())
        {    executor.submit(() -> {
                try {
                    Thread.sleep(100);
                    incomeTaxDept.payTax(taxPayer);
                    double revenue = incomeTaxDept.getTotalRevenue();
                    LOG.info("IncomeTax Department's total revenue after tax paid at client code is --->> " +revenue);


                    double returnAmount = incomeTaxDept.getFerdralTaxReturn(taxPayer);
                    LOG.info(taxPayer.getTaxPayerName() + " received the Federal returned Amount " + returnAmount);
                    revenue = incomeTaxDept.getTotalRevenueOptimisticRead();
                    LOG.info("IncomeTax Department's total revenue after getting Federal tax return at client code is --->> " +revenue);

                    double stateRetunedAmount = incomeTaxDept.getStateTaxReturnUsingConvertToWriteLock(taxPayer);
                    LOG.info(taxPayer.getTaxPayerName() + " received the State Tax Returned Amount = "+ stateRetunedAmount);

                    revenue = incomeTaxDept.getTotalRevenueOptimisticRead();
                    LOG.info("IncomeTax Department's total revenue after getting State tax return at client code is --->> " +revenue);

                    Thread.sleep(100);

                }
                catch (Exception e){}
        }
        );
        }
        executor.shutdown();

    }

    public static void registerTaxPayers()
    {
        for (int i = 0; i < NUMBER_OF_TAX_PAYER; i++) {

            TaxPayer taxPayer = new TaxPayer();
            taxPayer.setTaxPayerName("Payer-"+i);
            taxPayer.setTaxAmount(2000);
            taxPayer.setTaxPayerSsn("xxx-xxx-xxx" + i);
            incomeTaxDept.registerTaxPaye(taxPayer);
        }
    }
}
