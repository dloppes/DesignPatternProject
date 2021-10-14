/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Company;

/**
 *
 * @author Daniel Lopes
 */
public interface CompanyInterface {

    public Company.CompanyBuilder setCompanyName(String name);

    public Company.CompanyBuilder setCompanyGroup(String group);

}
