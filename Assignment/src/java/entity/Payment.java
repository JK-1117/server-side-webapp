/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JK
 */
@Entity
@Table(name = "payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByCustomerNumber", query = "SELECT p FROM Payment p WHERE p.paymentPK.customerNumber = :customerNumber")
    , @NamedQuery(name = "Payment.findByCheckNumber", query = "SELECT p FROM Payment p WHERE p.paymentPK.checkNumber = :checkNumber")
    , @NamedQuery(name = "Payment.findByPaymentDate", query = "SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate")
    , @NamedQuery(name = "Payment.findByAmount", query = "SELECT p FROM Payment p WHERE p.amount = :amount")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PaymentPK paymentPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;

    public Payment() {
    }

    public Payment(PaymentPK paymentPK) {
        this.paymentPK = paymentPK;
    }

    public Payment(PaymentPK paymentPK, Date paymentDate, BigDecimal amount) {
        this.paymentPK = paymentPK;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment(int customerNumber, String checkNumber) {
        this.paymentPK = new PaymentPK(customerNumber, checkNumber);
    }

    public PaymentPK getPaymentPK() {
        return paymentPK;
    }

    public void setPaymentPK(PaymentPK paymentPK) {
        this.paymentPK = paymentPK;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentPK != null ? paymentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentPK == null && other.paymentPK != null) || (this.paymentPK != null && !this.paymentPK.equals(other.paymentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Payment[ paymentPK=" + paymentPK + " ]";
    }
    
}
