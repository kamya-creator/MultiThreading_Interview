package org.example.StampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Logger;

public class IncomeTaxDept {

    public static final Logger LOG = Logger.getLogger(String.valueOf(IncomeTaxDept.class));
    private List<TaxPayer> taxPayersList ;
    private double totalRevenue;
    private final StampedLock sl = new StampedLock();

    public IncomeTaxDept(long revenue, int numberOFTaxPayers) {
        this.totalRevenue = revenue;
        taxPayersList = new ArrayList<>(numberOFTaxPayers);
    }
    /**
     * This method is to show the feature of writeLock() method  
     */
    public void payTax(TaxPayer taxPayer)
    {

        double taxAmount = taxPayer.getTaxAmount();
        long stamp = sl.writeLock();
        try{
            totalRevenue = taxAmount + totalRevenue;
            LOG.info(taxPayer.getTaxPayerName() + " paid tax, now Total Tax Revenue >>>>>>" + this.totalRevenue);
        }
        finally {
            sl.unlockWrite(stamp);
        }

    }

    public double getFerdralTaxReturn(TaxPayer taxPayer)
    {
        double incomeTaxReturnAmmount = taxPayer.getTaxAmount() * 10/100;
        long stamp = sl.writeLock();
        try {
            totalRevenue = totalRevenue - incomeTaxReturnAmmount;
        }finally {
            sl.unlockWrite(stamp);
        }
        return incomeTaxReturnAmmount;
    }

    public double getTotalRevenue()
    {
        long stamp = sl.readLock();
        try{
            return this.totalRevenue;
        }
        finally {
            sl.unlockRead(stamp);
        }
    }
    
    public double getTotalRevenueOptimisticRead()
    {
        long stamp = sl.tryOptimisticRead();
        double balance = this.totalRevenue;

        // calling validate(stamp) method to ensure that stamp is valid, if not then acquire the Read Lock
        if(!sl.validate(stamp))
        {
            LOG.info("stamp for tryOptimisticRead is valid now , Go for acquiring readLock()");
            stamp = sl.readLock();
        }

        try{
            balance = this.totalRevenue;
        }
        finally {
            sl.unlockRead(stamp);
        }

        return balance;
    }
    public double getStateTaxReturnUsingConvertToWriteLock(TaxPayer taxPayer)
    {
        double incomeTaxReturnedAmount = taxPayer.getTaxAmount() * 5/100;
        long stamp = sl.readLock();

        // Trying to upgrade read lock to write lock
        stamp = sl.tryConvertToWriteLock(stamp);

        // Checking if tryConvertToWriteLock get success otherwise call writeLock method
        if(stamp == 0L)
        {
            LOG.info("stamp is zero for tryConvertToWriteLock(), so acquiring the write lock");
            stamp = sl.writeLock();
        }
        try{
            totalRevenue = totalRevenue - incomeTaxReturnedAmount;
        }
        finally {
            sl.unlockWrite(stamp);
        }
        return incomeTaxReturnedAmount;
    }

    public void registerTaxPaye(TaxPayer taxPayer)
    {
        taxPayersList.add(taxPayer);
    }

    public List<TaxPayer> getTaxPayersList()
    {
        return  taxPayersList;
    }
}
