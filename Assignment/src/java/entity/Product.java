/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JK
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.productName")
    , @NamedQuery(name = "Product.findByProductCode", query = "SELECT p FROM Product p WHERE p.productCode = :productCode")
    , @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
    , @NamedQuery(name = "Product.findByProductScale", query = "SELECT p FROM Product p WHERE p.productScale = :productScale")
    , @NamedQuery(name = "Product.findByProductVendor", query = "SELECT p FROM Product p WHERE p.productVendor = :productVendor")
    , @NamedQuery(name = "Product.findByQuantityInStock", query = "SELECT p FROM Product p WHERE p.quantityInStock = :quantityInStock")
    , @NamedQuery(name = "Product.findByBuyPrice", query = "SELECT p FROM Product p WHERE p.buyPrice = :buyPrice")
    , @NamedQuery(name = "Product.findByMsrp", query = "SELECT p FROM Product p WHERE p.msrp = :msrp")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "productCode")
    private String productCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "productName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "productScale")
    private String productScale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "productVendor")
    private String productVendor;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "productDescription")
    private String productDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantityInStock")
    private short quantityInStock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyPrice")
    private BigDecimal buyPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MSRP")
    private BigDecimal msrp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Orderdetail> orderdetailList;
    @JoinColumn(name = "productLine", referencedColumnName = "productLine")
    @ManyToOne(optional = false)
    private ProductLine productLine;

    public Product() {
    }

    public Product(String productCode) {
        this.productCode = productCode;
    }

    public Product(String productCode, String productName, String productScale, String productVendor, String productDescription, short quantityInStock, BigDecimal buyPrice, BigDecimal msrp) {
        this.productCode = productCode;
        this.productName = productName;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public short getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(short quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    @XmlTransient
    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productCode != null ? productCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productCode == null && other.productCode != null) || (this.productCode != null && !this.productCode.equals(other.productCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ productCode=" + productCode + " ]";
    }
    
}
