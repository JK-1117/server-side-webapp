/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JK
 */
@Entity
@Table(name = "orderdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o")
    , @NamedQuery(name = "Orderdetail.findByOrderNumber", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.orderNumber = :orderNumber")
    , @NamedQuery(name = "Orderdetail.findByProductCode", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailPK.productCode = :productCode")
    , @NamedQuery(name = "Orderdetail.findByQuantityOrdered", query = "SELECT o FROM Orderdetail o WHERE o.quantityOrdered = :quantityOrdered")
    , @NamedQuery(name = "Orderdetail.findByPriceEach", query = "SELECT o FROM Orderdetail o WHERE o.priceEach = :priceEach")
    , @NamedQuery(name = "Orderdetail.findByOrderLineNumber", query = "SELECT o FROM Orderdetail o WHERE o.orderLineNumber = :orderLineNumber")
    , @NamedQuery(name = "Orderdetail.getTopSalesProduct", query = "SELECT o.product FROM Orderdetail o GROUP BY o.product ORDER BY SUM(o.quantityOrdered) DESC")
    , @NamedQuery(name = "Orderdetail.getQuantitySoldByProduct", query = "SELECT SUM(o.quantityOrdered) AS quantitySold FROM Orderdetail o WHERE o.product = :product")})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderdetailPK orderdetailPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantityOrdered")
    private int quantityOrdered;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "priceEach")
    private BigDecimal priceEach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderLineNumber")
    private short orderLineNumber;
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;
    @JoinColumn(name = "productCode", referencedColumnName = "productCode", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Orderdetail() {
    }

    public Orderdetail(OrderdetailPK orderdetailPK) {
        this.orderdetailPK = orderdetailPK;
    }

    public Orderdetail(OrderdetailPK orderdetailPK, int quantityOrdered, BigDecimal priceEach, short orderLineNumber) {
        this.orderdetailPK = orderdetailPK;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }

    public Orderdetail(int orderNumber, String productCode) {
        this.orderdetailPK = new OrderdetailPK(orderNumber, productCode);
    }

    public OrderdetailPK getOrderdetailPK() {
        return orderdetailPK;
    }

    public void setOrderdetailPK(OrderdetailPK orderdetailPK) {
        this.orderdetailPK = orderdetailPK;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public short getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(short orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderdetailPK != null ? orderdetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.orderdetailPK == null && other.orderdetailPK != null) || (this.orderdetailPK != null && !this.orderdetailPK.equals(other.orderdetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orderdetail[ orderdetailPK=" + orderdetailPK + " ]";
    }
    
}
