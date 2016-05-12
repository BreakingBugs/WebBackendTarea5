package py.una.pol.web.tarea6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Date date = new Date();

    private Double amount;

    @JsonIgnore
    private Customer customer;

    public Date getDate() {
        //defensive copy
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        //defensive copy
        this.date = new Date(date.getTime());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
