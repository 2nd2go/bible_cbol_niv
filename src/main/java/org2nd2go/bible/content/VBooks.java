/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org2nd2go.bible.content;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mark
 */
@Entity
@Table(name = "v_books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VBooks.findAll", query = "SELECT v FROM VBooks v"),
    @NamedQuery(name = "VBooks.findById", query = "SELECT v FROM VBooks v WHERE v.id = :id"),
    @NamedQuery(name = "VBooks.findByBook", query = "SELECT v FROM VBooks v WHERE v.book = :book")})
public class VBooks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @Id
    private int id;
    @Size(max = 144)
    @Column(name = "BOOK")
    private String book;

    public VBooks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

}
